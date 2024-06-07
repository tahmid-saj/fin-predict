package com.ts.finpredict.FinPredict.model.entity;

import java.util.Map;
import java.util.TreeMap;

public class Predictor {

    private String currentDayPredictionDate;
    private float currentDayPredictionPrice;

    private Map<String, Float> currentWeekPredictions = new TreeMap<String, Float>();

    public Predictor() {

    }

    public Predictor(String currentDayPredictionDate, float currentDayPredictionPrice, Map<String, Float> currentWeekPredictions) {
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

    public float getCurrentDayPredictionPrice() {
        return currentDayPredictionPrice;
    }

    public void setCurrentDayPredictionPrice(float currentDayPredictionPrice) {
        this.currentDayPredictionPrice = currentDayPredictionPrice;
    }

    public Map<String, Float> getCurrentWeekPredictions() {
        return currentWeekPredictions;
    }

    public void setCurrentWeekPredictions(Map<String, Float> currentWeekPredictions) {
        this.currentWeekPredictions = currentWeekPredictions;
    }
}
