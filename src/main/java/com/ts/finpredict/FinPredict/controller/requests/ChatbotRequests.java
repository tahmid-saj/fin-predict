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
//        return "Sure, I'd be happy to help with some general financial advice. Could you provide more details about " +
//                "your financial situation or specific areas you need advice on? For example, are you looking for guidance " +
//                "on budgeting, saving, investing, debt management, retirement planning, or something else? The more specific " +
//                "you can be, the better advice I can offer.";

        String res = "";
        try {
            res = sendPost(url, userMessage);
        } finally {
            close();
        }

        return res;
    }

    private static void close() throws IOException {
        httpClient.close();
    }

    private static String sendPost(String url, String userMessage) throws Exception {
        HttpPost post = new HttpPost(url);
        System.out.println(url);
        System.out.println(userMessage);
        post.addHeader("Content-Type", "text/plain");

        final StringEntity userMessageBody = new StringEntity(userMessage.toString());
        post.setEntity(userMessageBody);

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

            String message = "";
            if (jsonResp.has("message")) {
                message = jsonResp.get("message").toString();
            }

            System.out.println(message);

            return message.substring(1, message.length() - 1);
        }
    }
}
