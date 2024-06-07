package com.ts.finpredict.FinPredict.controller.requests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

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
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatbotRequests {
    // one instance, reuse
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public ChatbotRequests() {

    }

    public static String getChatbotResponse(String url, String userMessage) throws Exception {
        String res = "";
        try {
            res = sendPost(url, userMessage);
        } finally {
//            close();
        }

        return res;
    }

    private static void close() throws IOException {
        httpClient.close();
    }

    private static String sendPost(String url, String userMessage) throws Exception {
        HttpPost post = new HttpPost(url);

//      request headers
        post.addHeader("Content-Type", "application/json");

        final String jsonBody = "{\"userMessage\":" + "\"" + userMessage+ "\"" +
                "}";
        final StringEntity entityJsonBody = new StringEntity(jsonBody);
        post.setEntity(entityJsonBody);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            String retSrc = EntityUtils.toString(response.getEntity());

//            JSONObject result = new JSONObject(retSrc);
////            JSONArray tokenList = result.getJSONArray("names");
////            JSONObject message = result.getJSONObject("message");
//            String message = result.getString("message");

            JsonObject jsonResp = new Gson().fromJson(retSrc, JsonObject.class); // String to JSONObject

            String message = "";
            if (jsonResp.has("message")) {
                message = jsonResp.get("message").toString();
            }

            return message.substring(1, message.length() - 1);
        }
    }
}
