package com.ts.finpredict.FinPredict.model.service;

import com.ts.finpredict.FinPredict.model.entity.Stock;
import java.util.List;

public interface StockService {
    List<Stock> getAllStocks();
    Stock saveStock(Stock stock);
}