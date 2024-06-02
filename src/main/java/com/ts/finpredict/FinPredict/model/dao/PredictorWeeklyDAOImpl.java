package com.ts.finpredict.FinPredict.model.dao;

import com.ts.finpredict.FinPredict.model.entity.PredictorWeeklyEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PredictorWeeklyDAOImpl implements PredictorWeeklyDAO {

    private EntityManager entityManager;

    @Autowired
    public PredictorWeeklyDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(PredictorWeeklyEntity predictorWeeklyEntity) {
        entityManager.persist(predictorWeeklyEntity);
    }

    @Override
    public List<PredictorWeeklyEntity> findByDate(String currentDay) {
        TypedQuery<PredictorWeeklyEntity> findByDateQuery = entityManager.createQuery(
                "FROM PredictorWeeklyEntity where currentDay=:currentDay", PredictorWeeklyEntity.class
        );
        findByDateQuery.setParameter("currentDay", currentDay);

        return findByDateQuery.getResultList();
    }

    @Override
    public List<PredictorWeeklyEntity> findAll() {
        TypedQuery<PredictorWeeklyEntity> findAllQuery = entityManager.createQuery(
                "FROM PredictorWeeklyEntity order by currentDay asc", PredictorWeeklyEntity.class
        );

        return findAllQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(PredictorWeeklyEntity predictorWeeklyEntity) {
        entityManager.merge(predictorWeeklyEntity);
    }

    @Override
    @Transactional
    public void delete(String currentDay) {
        PredictorWeeklyEntity predictorWeeklyEntity = entityManager.find(PredictorWeeklyEntity.class, currentDay);
        entityManager.remove(predictorWeeklyEntity);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery(
                "DELETE FROM PredictorWeeklyEntity"
        ).executeUpdate();

        return numRowsDeleted;
    }
}
