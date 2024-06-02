package com.ts.finpredict.FinPredict.controller.advice;

import com.ts.finpredict.FinPredict.controller.requests.AdviceRequests;
import com.ts.finpredict.FinPredict.model.entity.Advice;

import java.util.List;
import java.util.Map;

public class AdviceWorker {
    public Advice advice = new Advice();
    public AdviceRequests adviceRequests = new AdviceRequests();

    public AdviceWorker() {
        this.generateAdvices();
    }

    public AdviceWorker(Advice advice) {
        this.advice = advice;

        this.generateAdvices();
    }

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public List<Map<String, String>> generateAdvices() {
        List<Map<String, String>> resAdvices = this.adviceRequests.getAdvices();
        this.advice.setAdvices(resAdvices);

        return resAdvices;
    }
}
