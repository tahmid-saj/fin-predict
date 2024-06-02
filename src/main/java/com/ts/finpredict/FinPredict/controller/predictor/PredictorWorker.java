package com.ts.finpredict.FinPredict.controller.predictor;

import com.ts.finpredict.FinPredict.controller.requests.PredictorRequests;
import com.ts.finpredict.FinPredict.model.entity.Predictor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class PredictorWorker {

    public Predictor predictor = new Predictor();
    public PredictorRequests predictorRequests = new PredictorRequests();

    public PredictorWorker() {
        this.generateCurrentDayPrediction();
        this.generateCurrentWeekPredictions();
    }

    public PredictorWorker(Predictor predictor, PredictorRequests predictorRequests) {
        this.predictor = predictor;
        this.predictorRequests = predictorRequests;
    }

    public Predictor getPredictor() {
        return predictor;
    }

    public void setPredictor(Predictor predictor) {
        this.predictor = predictor;
    }

    public int generateCurrentDayPrediction() {
        int resCurrentDayPrediction = this.predictorRequests.getPredictorCurrentDayResults();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.predictor.setCurrentDayPredictionDate(formatter.format(date));
        this.predictor.setCurrentDayPredictionPrice(resCurrentDayPrediction);

        return resCurrentDayPrediction;
    }

    public Map<String, Integer> generateCurrentWeekPredictions() {
        Map<String, Integer> resCurrentWeekPredictions = this.predictorRequests.getPredictorCurrentWeekResults();
        this.predictor.setCurrentWeekPredictions(resCurrentWeekPredictions);

        return resCurrentWeekPredictions;
    }
}
