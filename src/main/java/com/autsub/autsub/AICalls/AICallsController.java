package com.autsub.autsub.AICalls;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AICallsController {

    private final ChatGPTService aiCallsService;


    public AICallsController(ChatGPTService aiCallsService) {
        this.aiCallsService = aiCallsService;
    }


    @PostMapping("/search")
    public ResponseEntity<Object> searchChatGPT(@RequestBody ChatRequest chatRequest){
         aiCallsService.getChatGPTResponse(chatRequest);

       return ResponseEntity.ok()
       .contentType(MediaType.APPLICATION_JSON)
       .body(aiCallsService.getChatGPTResponse(chatRequest));
       
    }
    
}
