package com.autsub.autsub.AICalls;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SearchReqestDto { 
    @NotBlank(message = "the message to cahtGPT is blank")
    private String searchQuery;

}
