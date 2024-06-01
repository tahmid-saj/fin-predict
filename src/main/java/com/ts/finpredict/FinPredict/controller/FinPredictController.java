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

    @GetMapping("/")
    public String about(Model model) {
        model.addAttribute("navigationLinksHeaders", navigationLinksHeaders);
//        model.addAttribute("aboutCardsImages", aboutCardsImages);

        return "about/about";
    }
}
