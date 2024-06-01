package com.ts.finpredict.FinPredict.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

        return "marketdata/marketdata";
    }

    @GetMapping("/predictor")
    public String predictor(Model model) {
        model.addAttribute("predictorHeaders", predictorHeaders);
        model.addAttribute("predictorIntervals", predictorIntervals);

        return "predictor/predictor";
    }
}
