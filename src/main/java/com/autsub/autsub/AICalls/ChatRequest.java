package com.autsub.autsub.AICalls;


import java.util.List;

import com.autsub.autsub.CompanyPlan.CompanyPlan;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ChatRequest {
    @JsonProperty("model")  
    private String model;

    @JsonProperty("messages")  
    private List<Message> messages;

    @JsonProperty("max_tokens")
    private int maxTokens;

    public ChatRequest(String model, List<Message> messages, int maxTokens) {
        this.model = model;
        this.messages = messages;
        this.maxTokens = maxTokens;
    }

    public ChatRequest(String model, List<Message> messages){
        this.model = model;
        this.messages = messages;
    }

    public static class Message  {
        @JsonProperty("role")
        private String role;

        @JsonProperty(defaultValue = "")
        private String content;

        private List<CompanyPlan> companyPlans;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public Message(String role,List<CompanyPlan> companyPlans){
            this.role = role;
            this.companyPlans = companyPlans;
        }

        public List<CompanyPlan> getCompanyPlans(){
            return companyPlans;
        }

        public void setContent(List<CompanyPlan> companyPlans){
    //        this.content = List.
        }

    } 
}
    
