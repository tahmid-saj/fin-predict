package com.ts.finpredict.FinPredict.controller.requests;

import com.google.gson.JsonArray;
import com.ts.finpredict.FinPredict.model.entity.MarketData;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.entity.StringEntity;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class MarketDataRequests {
    // one instance, reuse
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public MarketDataRequests() {

    }

    public static Map<String, Integer> getMarketDataSearchResults(String url, MarketData marketData) throws Exception {
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

//        try {
//            res = sendPost(url, marketData);
//        } finally {
//            close();
//        }
//
//        return res;
    }

    private static void close() throws IOException {
        httpClient.close();
    }

    private static Map<String, Integer> sendPost(String url, MarketData marketData) throws Exception {
        HttpPost post = new HttpPost(url);
        System.out.println(url);
        System.out.println(marketData);

//        request headers
        post.addHeader("Content-Type", "application/json");

        final String jsonBody = "{\"category\":" + "\"" + marketData.getCategory() + "\"," +
                "\"ticker\":" + "\"" + marketData.getTicker() + "\"," +
                "\"interval\":" + "\"" + marketData.getInterval() + "\"," +
                "\"startDate\":" + "\"" + marketData.getStartDate() + "\"," +
                "\"endDate\":" + "\"" + marketData.getEndDate() + "\"," +
            "}";
        final StringEntity entityJsonBody = new StringEntity(jsonBody);
        post.setEntity(entityJsonBody);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

//            System.out.println("chatbot response: " + EntityUtils.toString(response.getEntity()));
            String retSrc = EntityUtils.toString(response.getEntity());

//            JSONObject result = new JSONObject(retSrc);
////            JSONArray tokenList = result.getJSONArray("names");
////            JSONObject message = result.getJSONObject("message");
//            String message = result.getString("message");
//            System.out.println(message);

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
