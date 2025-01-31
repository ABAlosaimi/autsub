package com.autsub.autsub.AICalls;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatRequest {
    
    @JsonProperty("model")
    private String model;
    private Message message;
    
    @JsonProperty("max_tokens")
    private int maxTokens;

    public ChatRequest(String model, Message message, int maxTokens) {
        this.model = model;
        this.message = message;
        this.maxTokens = maxTokens;
    }

    public ChatRequest(){}
    
        public static class Message {
            private String role;
            private String content;
    
            public Message(String role, String content) {
                this.role = role;
                this.content = content;
            }
    
            public String getRole() { return role; }
            public String getContent() { return content; }


            public void setRole(String role) {this.role = role;}

            public void setContent(String content) {this.content = content;}
         }

        public String getModel() {
            return model;
        }

        public Message getMessage() {
            return message;
        }

        public int getMaxTokens() {
            return maxTokens;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public void setMessage(String message) {
            this.message = new Message("user", message);
        }

        public void setMaxTokens(int maxTokens) {
            this.maxTokens = maxTokens;
        }
   
}
    
