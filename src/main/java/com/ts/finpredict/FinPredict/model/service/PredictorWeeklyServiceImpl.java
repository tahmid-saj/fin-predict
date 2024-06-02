package com.ts.finpredict.FinPredict.model.service;

import com.ts.finpredict.FinPredict.model.dao.PredictorWeeklyDAO;
import com.ts.finpredict.FinPredict.model.entity.PredictorWeeklyEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredictorWeeklyServiceImpl implements PredictorWeeklyService {

    private PredictorWeeklyDAO predictorWeeklyDAO;

    public PredictorWeeklyServiceImpl(PredictorWeeklyDAO predictorWeeklyDAO) {
        this.predictorWeeklyDAO = predictorWeeklyDAO;
    }

    @Override
    @Transactional
    public void save(PredictorWeeklyEntity predictorWeeklyEntity) {
        this.predictorWeeklyDAO.save(predictorWeeklyEntity);
    }

    @Override
    public List<PredictorWeeklyEntity> findByDate(String currentDay) {
        return this.predictorWeeklyDAO.findByDate(currentDay);
    }

    @Override
    public List<PredictorWeeklyEntity> findAll() {
        return this.predictorWeeklyDAO.findAll();
    }

    @Override
    @Transactional
    public void update(PredictorWeeklyEntity predictorWeeklyEntity) {
        this.predictorWeeklyDAO.update(predictorWeeklyEntity);
    }

    @Override
    @Transactional
    public void delete(String currentDay) {
        this.predictorWeeklyDAO.delete(currentDay);
    }

    @Override
    @Transactional
    public int deleteAll() {
        return this.predictorWeeklyDAO.deleteAll();
    }
}
