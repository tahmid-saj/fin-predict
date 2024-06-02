package com.ts.finpredict.FinPredict.model.entity;

import java.util.Map;
import java.util.TreeMap;

public class Predictor {

    private String currentDayPredictionDate;
    private int currentDayPredictionPrice;

    private Map<String, Integer> currentWeekPredictions = new TreeMap<String, Integer>();

    public Predictor() {

    }

    public Predictor(String currentDayPredictionDate, int currentDayPredictionPrice, Map<String, Integer> currentWeekPredictions) {
        this.currentDayPredictionDate = currentDayPredictionDate;
        this.currentDayPredictionPrice = currentDayPredictionPrice;
        this.currentWeekPredictions = currentWeekPredictions;
    }

    public String getCurrentDayPredictionDate() {
        return currentDayPredictionDate;
    }

    public void setCurrentDayPredictionDate(String currentDayPredictionDate) {
        this.currentDayPredictionDate = currentDayPredictionDate;
    }

    public int getCurrentDayPredictionPrice() {
        return currentDayPredictionPrice;
    }

    public void setCurrentDayPredictionPrice(int currentDayPredictionPrice) {
        this.currentDayPredictionPrice = currentDayPredictionPrice;
    }

    public Map<String, Integer> getCurrentWeekPredictions() {
        return currentWeekPredictions;
    }

    public void setCurrentWeekPredictions(Map<String, Integer> currentWeekPredictions) {
        this.currentWeekPredictions = currentWeekPredictions;
    }
}
