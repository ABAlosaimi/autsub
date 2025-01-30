package com.autsub.autsub.AICalls;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class ChatGPTRequest {

    private String model = "text-davinci-003";
    private String prompt;
    private int temperature = 1;

    @JsonAlias("max_tokens:")
    private int maxTokens = 100;
}
