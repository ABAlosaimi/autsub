package com.autsub.autsub.AICalls;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.autsub.autsub.AICalls.Config.RateLimiter;

import org.springframework.http.*;

@Service
public class ChatGPTService {
    
    private String apiKey = "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";

    private String apiUrl = "https://api.openai.com/v1/completions";

    private final RestTemplate restTemplate = new RestTemplate();

    private final RateLimiter rateLimiter;

    public ChatGPTService(){
      this.rateLimiter = new RateLimiter(60);
    }


    @SuppressWarnings("null")
    public String getChatGPTResponse(ChatRequest chatRequest) {
        if (!rateLimiter.tryConsume()) {
            throw new RuntimeException("Rate limit exceeded. Please try again later.");
        }
       
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<ChatRequest> entity = new HttpEntity<>(chatRequest, headers);

        try {
            ResponseEntity<ChatResponse> response = restTemplate.exchange(apiUrl,
             HttpMethod.POST,
              entity, 
              ChatResponse.class
              );

              Thread.sleep(1000);

            return response.getBody().getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
