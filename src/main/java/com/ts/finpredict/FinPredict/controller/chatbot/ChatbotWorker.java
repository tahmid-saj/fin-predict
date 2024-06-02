package com.ts.finpredict.FinPredict.controller.chatbot;

import com.ts.finpredict.FinPredict.controller.requests.ChatbotRequests;
import com.ts.finpredict.FinPredict.model.entity.Chatbot;

public class ChatbotWorker {
    public Chatbot chatbot = new Chatbot();
    public ChatbotRequests chatbotRequests = new ChatbotRequests();

    public ChatbotWorker() {

    }

    public ChatbotWorker(Chatbot chatbot) {
        this.chatbot = chatbot;
    }

    public Chatbot getChatbot() {
        return chatbot;
    }

    public void setChatbot(Chatbot chatbot) {
        this.chatbot = chatbot;
    }

    public String generateChatbotResponse() {
        String resChatbotResponse = this.chatbotRequests.getChatbotResponse(this.chatbot.getUserMessage());
        this.chatbot.setChatbotResponse(resChatbotResponse);

        return resChatbotResponse;
    }

    public void clearUserMessage() {
        this.chatbot.setUserMessage("");
    }
}
