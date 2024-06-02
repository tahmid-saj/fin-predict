package com.ts.finpredict.FinPredict.config;

import com.ts.finpredict.FinPredict.model.entity.PredictorDailyEntity;
import com.ts.finpredict.FinPredict.model.entity.PredictorWeeklyEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PredictorEntityConfig {

    @Bean("predictorDailyEntity")
    public PredictorDailyEntity predictorEntityCurrentDay() {
        return new PredictorDailyEntity();
    }

    @Bean("predictorWeeklyEntity")
    public PredictorWeeklyEntity predictorEntityCurrentWeek() {
        return new PredictorWeeklyEntity();
    }
}
