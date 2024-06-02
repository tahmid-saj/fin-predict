package com.ts.finpredict.FinPredict.controller;

import com.ts.finpredict.FinPredict.controller.marketdata.MarketDataWorker;
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

    MarketDataWorker marketDataWorker = new MarketDataWorker();

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
        model.addAttribute("marketData", marketDataWorker.marketData);
        model.addAttribute("marketDataSearchResults", marketDataWorker.marketData.getMarketDataSearchResults());

        return "marketdata/marketdata";
    }

    @PostMapping("/searchMarketData")
    public String searchMarketData(@ModelAttribute("marketData") MarketData marketData,
                                   Model model) {
        Map<String, Integer> marketDataSearchResults = marketDataWorker.searchMarketData();

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

        return "predictor/predictor";
    }

    @GetMapping("/advice")
    public String advice(Model model) {
        return "advice/advice";
    }
}
