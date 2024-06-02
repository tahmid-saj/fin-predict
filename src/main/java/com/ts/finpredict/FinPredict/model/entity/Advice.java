package com.ts.finpredict.FinPredict.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Advice {

    private List<Map<String, String>> advices = new ArrayList<>();

    public Advice() {

    }

    public Advice(List<Map<String, String>> advices) {
        this.advices = advices;
    }

    public List<Map<String, String>> getAdvices() {
        return advices;
    }

    public void setAdvices(List<Map<String, String>> advices) {
        this.advices = advices;
    }
}
