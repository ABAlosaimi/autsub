package com.autsub.autsub.AICalls;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ai")
public class AICallsController {

    private final AICallsServiceImp aiCallsService;


    public AICallsController(AICallsServiceImp aiCallsService) {
        this.aiCallsService = aiCallsService;
    }


    @PostMapping("/search")
    public ResponseEntity<Object> searchChatGPT(@Valid @RequestBody SearchReqestDto searchReqestDto){
         aiCallsService.searchChatGPT(searchReqestDto.getSearchQuery());

       return ResponseEntity.ok()
       .contentType(MediaType.APPLICATION_JSON)
       .body(aiCallsService.searchChatGPT(searchReqestDto.getSearchQuery()));
       
    }
    
}
