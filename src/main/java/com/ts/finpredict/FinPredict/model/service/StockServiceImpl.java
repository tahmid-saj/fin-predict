package com.ts.finpredict.FinPredict.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ts.finpredict.FinPredict.model.dao.StockRepository;
import com.ts.finpredict.FinPredict.model.entity.Stock;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }
}