package com.ts.finpredict.FinPredict.model.dao;

import com.ts.finpredict.FinPredict.model.entity.PredictorDailyEntity;
import com.ts.finpredict.FinPredict.model.entity.PredictorWeeklyEntity;

import java.util.List;

public interface PredictorWeeklyDAO {
    void save(PredictorWeeklyEntity predictorWeeklyEntity);

    List<PredictorWeeklyEntity> findByDate(String currentDay);

    List<PredictorWeeklyEntity> findAll();

    void update(PredictorWeeklyEntity predictorWeeklyEntity);

    void delete(String currentDay);

    int deleteAll();
}
