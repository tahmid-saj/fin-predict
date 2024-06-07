package com.ts.finpredict.FinPredict.controller.requests;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ts.finpredict.FinPredict.model.entity.MarketData;
import com.ts.finpredict.FinPredict.model.entity.Predictor;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PredictorRequests {
    // one instance, reuse
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public PredictorRequests() {

    }

    public static float getPredictorCurrentDayResults(String url) throws Exception {
        float res = 0;

        try {
            res = sendGetCurrentDay(url);
        } finally {
//            close();
        }

        return res;
    }

    public static Map<String, Float> getPredictorCurrentWeekResults(String url) throws Exception {
        Map<String, Float> res = new TreeMap<String, Float>();

//        Random rand = new Random();
//
//        float randNum = rand.nextFloat(10);
//
//        res.put("2024-05-01", Float.valueOf(123));
//        res.put("2024-05-02", Float.valueOf(123));
//        res.put("2024-05-03", Float.valueOf(123));

        try {
            res = sendGetCurrentWeek(url);
        } finally {
//            close();
        }

        return res;
    }

    private static void close() throws IOException {
        httpClient.close();
    }

    private static float sendGetCurrentDay(String url) throws Exception {
        HttpGet request = new HttpGet(url);

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            String retSrc = EntityUtils.toString(response.getEntity());
            JsonObject jsonResp = new Gson().fromJson(retSrc, JsonObject.class); // String to JSONObject

            String closingPrice = "";
            if (jsonResp.has("closingPrice")) {
                closingPrice = jsonResp.get("closingPrice").toString();
            }

            return Float.parseFloat(closingPrice.substring(1, closingPrice.length() - 1));
        }
    }

    public static Map<String, Float> sendGetCurrentWeek(String url) throws Exception {
        HttpGet request = new HttpGet(url);

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            String retSrc = EntityUtils.toString(response.getEntity());

            JsonObject jsonResp = new Gson().fromJson(retSrc, JsonObject.class); // String to JSONObject

            JsonArray resDates = new JsonArray();
            if (jsonResp.has("closingPriceDates")) {
                resDates = jsonResp.getAsJsonArray("closingPriceDates");
            }

            JsonArray resClosingPrices = new JsonArray();
            if (jsonResp.has("closingPrices")) {
                resClosingPrices = jsonResp.getAsJsonArray("closingPrices");
            }

            Map<String, Float> res = new TreeMap<String, Float>();
            for (int i = 0; i < resDates.size(); i++) {
                String tmp = resDates.get(i).toString();
                res.put(tmp.substring(1, tmp.length() - 1), resClosingPrices.get(i).getAsFloat());
            }

            return res;
        }
    }
}
