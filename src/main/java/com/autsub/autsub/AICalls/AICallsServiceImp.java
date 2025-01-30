package com.autsub.autsub.AICalls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class AICallsServiceImp {

    @Value("${OPEN_AI_URL}")
    private String OPEN_AI_URL;

    @Value("${OPEN_AI_KEY}")
    private String OPEN_AI_KEY;

    private final WebClient webClient;


    public AICallsServiceImp() {
        this.webClient = WebClient.builder()
                .baseUrl(OPEN_AI_URL)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + OPEN_AI_KEY)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public String searchChatGPT(String message){

       Map<String, Object> requestMap = Map.of(
        "model", "gpt-3.5-turbo",
        "message", message
       );

       return webClient.post()
       .bodyValue(requestMap)
       .retrieve()
       .bodyToMono(Map.class)
       .map(response -> (String) ((Map<String, Object>) ((java.util.List<?>) response.get("choices")).get(0)).get("text"))
       .block();

    }

   
    
}
