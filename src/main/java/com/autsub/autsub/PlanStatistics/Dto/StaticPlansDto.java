package com.autsub.autsub.PlanStatistics.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter 
public class StaticPlansDto {

     @NotBlank(message = "Titel is mandatory")
    private String[] titel;
    
    @NotBlank(message = "Category is mandatory")
    private String[] category;

    @NotBlank(message = "Description is mandatory")
    private String[] description;
    
    private String[] recurring;
    
    @NotBlank(message = "Price is mandatory")
    private int[] price;

    private boolean[] trial;


}