package com.ts.finpredict.FinPredict.controller.chatbot;

import com.ts.finpredict.FinPredict.controller.requests.ChatbotRequests;
import com.ts.finpredict.FinPredict.model.entity.Chatbot;
import org.springframework.beans.factory.annotation.Autowired;

public class ChatbotWorker {
    public Chatbot chatbot = new Chatbot();
    public ChatbotRequests chatbotRequests = new ChatbotRequests();

    @Autowired
    public ChatbotWorker() {

    }

    @Autowired
    public ChatbotWorker(Chatbot chatbot) {
        this.chatbot = chatbot;
    }

    public Chatbot getChatbot() {
        return chatbot;
    }

    public void setChatbot(Chatbot chatbot) {
        this.chatbot = chatbot;
    }

    public String generateChatbotResponse(String url, String userMessage) throws Exception {
        System.out.println(this.chatbot.getUserMessage());
        String resChatbotResponse = this.chatbotRequests.getChatbotResponse(url, userMessage);
        this.chatbot.setChatbotResponse(resChatbotResponse);

        return resChatbotResponse;
    }

    public void clearUserMessage() {
        this.chatbot.setUserMessage("");
    }
}
