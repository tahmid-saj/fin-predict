package com.ts.finpredict.FinPredict.model.service;

import com.ts.finpredict.FinPredict.model.entity.PredictorWeeklyEntity;

import java.util.List;

public interface PredictorWeeklyService {
    void save(PredictorWeeklyEntity predictorWeeklyEntity);

    List<PredictorWeeklyEntity> findByDate(String currentDay);

    List<PredictorWeeklyEntity> findAll();

    void update(PredictorWeeklyEntity predictorWeeklyEntity);

    void delete(String currentDay);

    int deleteAll();
}
