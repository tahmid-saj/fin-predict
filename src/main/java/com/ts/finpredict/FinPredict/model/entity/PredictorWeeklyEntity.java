package com.ts.finpredict.FinPredict.model.entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.context.annotation.Lazy;

@Entity
@Table(name="predictor_weekly")
@Lazy
public class PredictorWeeklyEntity {

    @Id
    @Column(name="current_day")
    private String currentDay;

    @Column(name="closing_price")
    private float closingPrice;

    public PredictorWeeklyEntity() {

    }

    public PredictorWeeklyEntity(String currentDay, float closingPrice) {
        this.currentDay = currentDay;
        this.closingPrice = closingPrice;
    }

    public String getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(String currentDay) {
        this.currentDay = currentDay;
    }

    public float getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(float closingPrice) {
        this.closingPrice = closingPrice;
    }

    @Override
    public String toString() {
        return "PredictorWeeklyEntity{" +
                "currentDay='" + currentDay + '\'' +
                ", closingPrice=" + closingPrice +
                '}';
    }

    @PostConstruct
    public void doStartUpStuff() {
        System.out.println("Starting " + getClass().getSimpleName());
    }

    @PostConstruct
    public void doCleanupUpStuff() {
        System.out.println("Cleaning " + getClass().getSimpleName());
    }
}
