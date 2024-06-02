package com.ts.finpredict.FinPredict.model.dao;

import com.ts.finpredict.FinPredict.model.entity.PredictorDailyEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PredictorDailyDAOImpl implements PredictorDailyDAO {

    private EntityManager entityManager;

    @Autowired
    public PredictorDailyDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(PredictorDailyEntity predictorDailyEntity) {
        entityManager.persist(predictorDailyEntity);
    }

    @Override
    public List<PredictorDailyEntity> findByDate(String currentDay) {
        TypedQuery<PredictorDailyEntity> findByDateQuery = entityManager.createQuery(
                "FROM PredictorDailyEntity where currentDay=:currentDay", PredictorDailyEntity.class
        );
        findByDateQuery.setParameter("currentDay", currentDay);

        return findByDateQuery.getResultList();
    }

    @Override
    public List<PredictorDailyEntity> findAll() {
        TypedQuery<PredictorDailyEntity> findAllQuery = entityManager.createQuery(
                "FROM PredictorDailyEntity order by currentDay asc", PredictorDailyEntity.class
        );

        return findAllQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(PredictorDailyEntity predictorDailyEntity) {
        entityManager.merge(predictorDailyEntity);
    }

    @Override
    @Transactional
    public void delete(String currentDay) {
        PredictorDailyEntity predictorDailyEntity = entityManager.find(PredictorDailyEntity.class, currentDay);
        entityManager.remove(predictorDailyEntity);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery(
                "DELETE FROM PredictorDailyEntity"
        ).executeUpdate();

        return numRowsDeleted;
    }
}
