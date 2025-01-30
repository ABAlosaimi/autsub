package com.autsub.autsub.AICalls;

import java.util.List;

import lombok.Data;
@Data
public class ChatGPTResponse {
    private List<ChatGPTChoice> choices;

}
