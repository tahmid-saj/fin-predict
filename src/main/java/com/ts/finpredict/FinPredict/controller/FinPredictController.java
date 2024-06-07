package com.ts.finpredict.FinPredict.controller;

import com.ts.finpredict.FinPredict.controller.advice.AdviceWorker;
import com.ts.finpredict.FinPredict.controller.chatbot.ChatbotWorker;
import com.ts.finpredict.FinPredict.controller.marketdata.MarketDataWorker;
import com.ts.finpredict.FinPredict.controller.predictor.PredictorWorker;
import com.ts.finpredict.FinPredict.model.entity.Chatbot;
import com.ts.finpredict.FinPredict.model.entity.MarketData;
//import com.ts.finpredict.FinPredict.model.entity.PredictorDailyEntity;
//import com.ts.finpredict.FinPredict.model.entity.PredictorWeeklyEntity;
//import com.ts.finpredict.FinPredict.model.service.PredictorDailyService;
//import com.ts.finpredict.FinPredict.model.service.PredictorWeeklyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class FinPredictController {
    @Value("${navigationLinksHeaders}")
    private List<String> navigationLinksHeaders;

//    @Value("${aboutCardsImages}")
//    private List<String> aboutCardsImages;

    @Value("${marketDataCategories}")
    private List<String> marketDataCategories;

    @Value("${marketDataIntervals}")
    private List<String> marketDataIntervals;

    @Value("${predictorHeaders}")
    private List<String> predictorHeaders;

    @Value("${predictorIntervals}")
    private List<String> predictorIntervals;

    @Value("${finpredictapiurl}")
    private String finpredictapiurl;

    @Value("${finpredictapichatbotpath}")
    private String finpredictapichatbotpath;

    @Value("${finpredictapimarketdatapath}")
    private String finpredictapimarketdatapath;

    @Value("${finpredictapipredictorcurrentdaypath}")
    private String finpredictapipredictorcurrentdaypath;

    @Value("${finpredictapipredictorcurrentweekpath}")
    private String finpredictapipredictorcurrentweekpath;

    MarketDataWorker marketDataWorker = new MarketDataWorker();

    AdviceWorker adviceWorker = new AdviceWorker();
    ChatbotWorker chatbotWorker = new ChatbotWorker();

//    without using @Service annotation: -------------------------------------------------------------------------------------------------------
//    private PredictorDailyEntity predictorDailyEntity;
//    private PredictorWeeklyEntity predictorWeeklyEntity;

    @Autowired
    public FinPredictController() {
    }

//    constructor injection
//    @Autowired
//    public FinPredictController(
//            @Qualifier("PredictorDailyEntity") PredictorDailyEntity predictorDailyEntity,
//            @Qualifier("PredictorWeeklyEntity") PredictorWeeklyEntity predictorWeeklyEntity
//    ) throws Exception {
//        this.predictorDailyEntity = predictorDailyEntity;
//        this.predictorWeeklyEntity = predictorWeeklyEntity;
//    }

//    setter injection
//    @Autowired
//    public void setPredictorDailyEntity(PredictorDailyEntity predictorDailyEntity) {
//        this.predictorDailyEntity = predictorDailyEntity;
//    }

//    setter injection
//    @Autowired
//    public void setPredictorWeeklyEntity(PredictorWeeklyEntity predictorWeeklyEntity) {
//        this.predictorWeeklyEntity = predictorWeeklyEntity;
//    }

//    using @Service annotation: -------------------------------------------------------------------------------------------------------
//    private PredictorDailyService predictorDailyService;
//    private PredictorWeeklyService predictorWeeklyService;
//
////    constructor injection
//    @Autowired
//    public FinPredictController(
//            PredictorDailyService predictorDailyService,
//            PredictorWeeklyService predictorWeeklyService
//    ) {
//      this.predictorDailyService = predictorDailyService;
//      this.predictorWeeklyService = predictorWeeklyService;
//    }
//
////    setter injection
//    @Autowired
//    public void setPredictorDailyService(PredictorDailyService predictorDailyService) {
//        this.predictorDailyService = predictorDailyService;
//    }
//
////    setter injection
//    @Autowired
//    public void setPredictorWeeklyService(PredictorWeeklyService predictorWeeklyService) {
//        this.predictorWeeklyService = predictorWeeklyService;
//    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String about(Model model) {
        model.addAttribute("navigationLinksHeaders", navigationLinksHeaders);
//        model.addAttribute("aboutCardsImages", aboutCardsImages);

        return "about/about";
    }

    @GetMapping("/market")
    public String market(Model model) {
        model.addAttribute("marketDataCategories", marketDataCategories);
        model.addAttribute("marketDataIntervals", marketDataIntervals);

        MarketData marketData = marketDataWorker.marketData;

        model.addAttribute("marketData", marketData);
        model.addAttribute("marketDataSearchResults", marketData.getMarketDataSearchResults());

        return "marketdata/marketdata";
    }

    @PostMapping("/searchMarketData")
    public String searchMarketData(
            @Valid @ModelAttribute("marketData") MarketData marketData,
            BindingResult bindingResult,
            Model model
    ) throws Exception {
        System.out.println(bindingResult.toString());

        if (bindingResult.hasErrors()) {
            model.addAttribute("marketDataCategories", marketDataCategories);
            model.addAttribute("marketDataIntervals", marketDataIntervals);

            model.addAttribute("marketData", marketData);
            model.addAttribute("marketDataSearchResults", marketData.getMarketDataSearchResults());

            return "marketdata/marketdata";
        }

        Map<String, Integer> marketDataSearchResults = marketDataWorker.searchMarketData(
                finpredictapiurl + finpredictapimarketdatapath,
                marketData
        );

        return "redirect:market";
    }

    @GetMapping("/clearMarketData")
    public String clearMarketData(@ModelAttribute("marketData") MarketData marketData,
                                  Model model) {
        marketDataWorker.clearMarketDataForm();

        return "redirect:market";
    }

    @GetMapping("/predictor")
    public String predictor(Model model) throws Exception {
        PredictorWorker predictorWorker = new PredictorWorker(
                finpredictapiurl + finpredictapipredictorcurrentdaypath,
                finpredictapiurl + finpredictapipredictorcurrentweekpath
        );

        model.addAttribute("predictorHeaders", predictorHeaders);
        model.addAttribute("predictorIntervals", predictorIntervals);

        model.addAttribute("predictorCurrentDayPredictionDate",
                predictorWorker.predictor.getCurrentDayPredictionDate());
        model.addAttribute("predictorCurrentDayPredictionPrice",
                predictorWorker.predictor.getCurrentDayPredictionPrice());
        model.addAttribute("predictorCurrentWeekPredictions",
                predictorWorker.predictor.getCurrentWeekPredictions());

        return "predictor/predictor";
    }

    @GetMapping("/advice")
    public String advice(Model model) {
        model.addAttribute("advices", adviceWorker.advice.getAdvices());

        Chatbot chatbot = chatbotWorker.chatbot;

        model.addAttribute("chatbot", chatbot);
        model.addAttribute("chatbotResponse", chatbot.getChatbotResponse());

        return "advice/advice";
    }

    @PostMapping("/generateChatbotResponse")
    public String generateChatbotResponse(@ModelAttribute("chatbot") Chatbot chatbot,
                                          Model model) throws Exception {
        System.out.println(finpredictapiurl + finpredictapichatbotpath);
        String resChatbotResponse = chatbotWorker.generateChatbotResponse(
                finpredictapiurl + finpredictapichatbotpath,
                chatbot.getUserMessage()
        );

        return "redirect:advice";
    }

    @GetMapping("/clearUserMessage")
    public String clearUserMessage(Model model) {
        chatbotWorker.clearUserMessage();

        return "redirect:advice";
    }
}
