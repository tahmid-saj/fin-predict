package com.ts.finpredict.FinPredict.controller;

import com.ts.finpredict.FinPredict.controller.advice.AdviceWorker;
import com.ts.finpredict.FinPredict.controller.chatbot.ChatbotWorker;
import com.ts.finpredict.FinPredict.controller.marketdata.MarketDataWorker;
import com.ts.finpredict.FinPredict.controller.predictor.PredictorWorker;
import com.ts.finpredict.FinPredict.model.entity.Chatbot;
import com.ts.finpredict.FinPredict.model.entity.MarketData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    MarketDataWorker marketDataWorker = new MarketDataWorker();
    PredictorWorker predictorWorker = new PredictorWorker();
    AdviceWorker adviceWorker = new AdviceWorker();
    ChatbotWorker chatbotWorker = new ChatbotWorker();

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
    public String searchMarketData(@ModelAttribute("marketData") MarketData marketData,
                                   Model model) {
        Map<String, Integer> marketDataSearchResults = marketDataWorker.searchMarketData(marketData);

        return "redirect:market";
    }

    @GetMapping("/clearMarketData")
    public String clearMarketData(@ModelAttribute("marketData") MarketData marketData,
                                  Model model) {
        marketDataWorker.clearMarketDataForm();

        return "redirect:market";
    }

    @GetMapping("/predictor")
    public String predictor(Model model) {
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
        String resChatbotResponse = chatbotWorker.generateChatbotResponse(finpredictapiurl + finpredictapichatbotpath,
                chatbot.getUserMessage());

        return "redirect:advice";
    }

    @GetMapping("/clearUserMessage")
    public String clearUserMessage(Model model) {
        chatbotWorker.clearUserMessage();

        return "redirect:advice";
    }
}