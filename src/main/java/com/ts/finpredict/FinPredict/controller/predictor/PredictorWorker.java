package com.ts.finpredict.FinPredict.controller.predictor;

import com.ts.finpredict.FinPredict.controller.requests.PredictorRequests;
import com.ts.finpredict.FinPredict.model.entity.Predictor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class PredictorWorker {

    public Predictor predictor = new Predictor();
    public PredictorRequests predictorRequests = new PredictorRequests();

    public PredictorWorker(String urlCurrentDay, String urlCurrentWeek) throws Exception {
        this.generateCurrentDayPrediction(urlCurrentDay);
        this.generateCurrentWeekPredictions(urlCurrentWeek);
    }

    public PredictorWorker(String urlCurrentDay, String urlCurrentWeek, Predictor predictor, PredictorRequests predictorRequests) throws Exception {
        this.predictor = predictor;
        this.predictorRequests = predictorRequests;

        this.generateCurrentDayPrediction(urlCurrentDay);
        this.generateCurrentWeekPredictions(urlCurrentWeek);
    }

    public void setPredictor(Predictor predictor) {
        this.predictor = predictor;
    }

    public Predictor getPredictor() {
        return predictor;
    }

    public float generateCurrentDayPrediction(String urlCurrentDay) throws Exception {
        float resCurrentDayPrediction = this.predictorRequests.getPredictorCurrentDayResults(urlCurrentDay);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.predictor.setCurrentDayPredictionDate(formatter.format(date));
        this.predictor.setCurrentDayPredictionPrice(resCurrentDayPrediction);

        return resCurrentDayPrediction;
    }

    public Map<String, Float> generateCurrentWeekPredictions(String urlCurrentWeek) throws Exception {
        Map<String, Float> resCurrentWeekPredictions = this.predictorRequests.getPredictorCurrentWeekResults(urlCurrentWeek);
        this.predictor.setCurrentWeekPredictions(resCurrentWeekPredictions);

        return resCurrentWeekPredictions;
    }
}
