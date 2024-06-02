package com.ts.finpredict.FinPredict.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="predictor_daily")
public class PredictorDailyEntity {
    @Column(name="current_day")
    private String currentDay;

    @Column(name="closing_price")
    private int closingPrice;

    public PredictorDailyEntity() {

    }

    public PredictorDailyEntity(String currentDay, int closingPrice) {
        this.currentDay = currentDay;
        this.closingPrice = closingPrice;
    }

    public String getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(String currentDay) {
        this.currentDay = currentDay;
    }

    public int getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(int closingPrice) {
        this.closingPrice = closingPrice;
    }

    @Override
    public String toString() {
        return "PredictorDailyEntity{" +
                "currentDay='" + currentDay + '\'' +
                ", closingPrice='" + closingPrice + '\'' +
                '}';
    }
}
