package com.ts.finpredict.FinPredict.controller.requests;

import com.ts.finpredict.FinPredict.model.entity.Predictor;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class PredictorRequests {

    public PredictorRequests() {

    }

    public static int getPredictorCurrentDayResults() {
        return 201;
    }

    public static Map<String, Integer> getPredictorCurrentWeekResults() {
        Map<String, Integer> res = new TreeMap<String, Integer>();

        Random rand = new Random();

        int randNum = rand.nextInt(10);

        if (randNum <= 5) {
            res.put("2024-05-01", 211);
            res.put("2024-05-02", 301);
            res.put("2024-05-03", 101);
        } else {
            res.put("2024-05-01", 400);
            res.put("2024-05-02", 301);
            res.put("2024-05-03", 701);
        }

        return res;
    }
}
