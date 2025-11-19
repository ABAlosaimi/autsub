package com.autsub.autsub.PlanStatistics.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StaticPlansDto { // it may be refactored to a map of <String, Map<String,String>> later 

    @NotBlank(message = "Titel is mandatory")
    private String[] titel;
    
    @NotBlank(message = "Category is mandatory")
    private String[] category;

    @NotBlank(message = "Description is mandatory")
    private String[] description;
    
    private String[] recurring;
    
    @NotNull(message = "Price is mandatory")
    private int[] price;

    private boolean[] trial;

}