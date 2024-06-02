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

    public static int getPredictorCurrentDayResults(String url) throws Exception {
        int res = 0;

//        try {
//            res = sendGetCurrentDay(url);
//        } finally {
//            close();
//        }

        return res;
    }

    public static Map<String, Integer> getPredictorCurrentWeekResults(String url) throws Exception {
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

//        try {
//            res = sendGetCurrentWeek(url);
//        } finally {
//            close();
//        }

        return res;
    }

    private static void close() throws IOException {
        httpClient.close();
    }

    private static int sendGetCurrentDay(String url) throws Exception {
        HttpGet request = new HttpGet(url);

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            String result = null;
            if (entity != null) {
                // return it as a String
                result = EntityUtils.toString(entity);
            }

            return Integer.parseInt(result);
        }
    }

    public static Map<String, Integer> sendGetCurrentWeek(String url) throws Exception {
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
            System.out.println(resDates);

            JsonArray resClosingPrices = new JsonArray();
            if (jsonResp.has("closingPrices")) {
                resClosingPrices = jsonResp.getAsJsonArray("closingPrices");
            }
            System.out.println(resClosingPrices);

            Map<String, Integer> res = new TreeMap<String, Integer>();
            for (int i = 0; i < resDates.size(); i++) {
                res.put(resDates.get(i).toString(), resClosingPrices.get(i).getAsInt());
            }

            return res;
        }
    }
}
