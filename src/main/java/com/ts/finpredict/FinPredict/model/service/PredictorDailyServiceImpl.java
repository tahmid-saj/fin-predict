//package com.ts.finpredict.FinPredict.model.service;
//
//import com.ts.finpredict.FinPredict.model.dao.PredictorDailyDAO;
//import com.ts.finpredict.FinPredict.model.entity.PredictorDailyEntity;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class PredictorDailyServiceImpl implements PredictorDailyService {
//
//    private PredictorDailyDAO predictorDailyDAO;
//
//    public PredictorDailyServiceImpl(PredictorDailyDAO predictorDailyDAO) {
//        this.predictorDailyDAO = predictorDailyDAO;
//    }
//
//    @Override
//    @Transactional
//    public void save(PredictorDailyEntity predictorDailyEntity) {
//        this.predictorDailyDAO.save(predictorDailyEntity);
//    }
//
//    @Override
//    public List<PredictorDailyEntity> findByDate(String currentDay) {
////        TODO: need proper error responses for CRUD in service
//        List<PredictorDailyEntity> predictorDailyEntities = this.predictorDailyDAO.findByDate(currentDay);
//
//        if (predictorDailyEntities == null) {
//            throw new RuntimeException("Did not find Predictor Daily Entity for date: " + currentDay);
//        }
//
//        return predictorDailyEntities;
//    }
//
//    @Override
//    public List<PredictorDailyEntity> findAll() {
//        return this.predictorDailyDAO.findAll();
//    }
//
//    @Override
//    @Transactional
//    public void update(PredictorDailyEntity predictorDailyEntity) {
//        this.predictorDailyDAO.update(predictorDailyEntity);
//    }
//
//    @Override
//    @Transactional
//    public void delete(String currentDay) {
//        this.predictorDailyDAO.delete(currentDay);
//    }
//
//    @Override
//    @Transactional
//    public int deleteAll() {
//        return this.predictorDailyDAO.deleteAll();
//    }
//}
