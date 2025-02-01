package com.autsub.autsub.AICalls;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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


    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/search")
    public ResponseEntity<Object> searchChatGPT(@RequestBody String userMessage){
         String response = aiCallsService.getChatGPTResponse(userMessage);

       return ResponseEntity.ok()
       .contentType(MediaType.APPLICATION_JSON)
       .body(response);
    }
    
}
