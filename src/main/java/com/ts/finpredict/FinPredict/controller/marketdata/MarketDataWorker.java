package com.ts.finpredict.FinPredict.controller.marketdata;

import com.ts.finpredict.FinPredict.controller.requests.MarketDataRequests;
import com.ts.finpredict.FinPredict.model.entity.MarketData;

import java.util.*;

public class MarketDataWorker {
    public MarketData marketData = new MarketData();
    public MarketDataRequests marketDataRequests = new MarketDataRequests();

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

    public Map<String, Integer> searchMarketData(String url, MarketData marketData) throws Exception {
        Map<String, Integer> marketDataSearchResults = marketDataRequests.getMarketDataSearchResults(url, marketData);
        this.marketData.setMarketDataSearchResults(marketDataSearchResults);

        return marketDataSearchResults;
    }

    public void clearMarketDataForm() {
        this.marketData.setCategory("");
        this.marketData.setTicker("");
        this.marketData.setInterval("");
        this.marketData.setStartDate("");
        this.marketData.setEndDate("");
    }
}
