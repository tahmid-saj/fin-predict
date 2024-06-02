package com.ts.finpredict.FinPredict.model.entity;

import java.util.*;

public class MarketData {
    private String category;
    private String ticker;
    private String interval;
    private String startDate;
    private String endDate;

    private Map<String, Integer> marketDataSearchResults = new TreeMap<String, Integer>();
    public MarketData() {

    }

    public MarketData(String category, String ticker, String interval, String startDate, String endDate) {
        this.category = category;
        this.ticker = ticker;
        this.interval = interval;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public MarketData(Map<String, Integer> marketDataSearchResults) {
        this.marketDataSearchResults = marketDataSearchResults;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Map<String, Integer> getMarketDataSearchResults() {
        return marketDataSearchResults;
    }

    public void setMarketDataSearchResults(Map<String, Integer> marketDataSearchResults) {
        this.marketDataSearchResults = marketDataSearchResults;
    }
}
