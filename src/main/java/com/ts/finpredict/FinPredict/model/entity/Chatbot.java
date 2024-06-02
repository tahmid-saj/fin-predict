package com.ts.finpredict.FinPredict.model.entity;

public class Chatbot {

    private String userMessage = "";
    private String chatbotResponse = null;

    public Chatbot() {

    }

    public Chatbot(String userMessage, String chatbotResponse) {
        this.userMessage = userMessage;
        this.chatbotResponse = chatbotResponse;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getChatbotResponse() {
        return chatbotResponse;
    }

    public void setChatbotResponse(String chatbotResponse) {
        this.chatbotResponse = chatbotResponse;
    }
}
