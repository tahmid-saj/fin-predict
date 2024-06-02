package com.ts.finpredict.FinPredict.controller.marketdata;

import com.ts.finpredict.FinPredict.model.entity.MarketData;

import java.util.*;

public class MarketDataWorker {
    public MarketData marketData = new MarketData();

    public MarketDataWorker() {

    }

    public MarketDataWorker(MarketData marketData) {
        this.marketData = marketData;
    }

    public MarketData getMarketData() {
        return marketData;
    }

    public void setMarketData(MarketData marketData) {
        this.marketData = marketData;
    }

    public Map<String, Integer> searchMarketData() {
        Map<String, Integer> res = new TreeMap<String, Integer>();

        Random rand = new Random();

        int randNum = rand.nextInt(10);

        if (randNum <= 5) {
            res.put("2024-05-01", 211);
            res.put("2024-05-02", 301);
            res.put("2024-05-03", 101);
        } else {
            res.put("2024-05-01", 400);
            res.put("2024-05-02", 301);
            res.put("2024-05-03", 701);
        }

        return res;
    }

    public void clear() {
        this.marketData.setCategory("");
        this.marketData.setTicker("");
        this.marketData.setInterval("");
        this.marketData.setStartDate("");
        this.marketData.setEndDate("");
    }
}
