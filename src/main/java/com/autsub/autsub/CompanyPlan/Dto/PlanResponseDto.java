package com.autsub.autsub.CompanyPlan.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data 
public class PlanResponseDto {
    
    public PlanResponseDto(@NonNull Long plan_id, @NotBlank(message = "Titel is mandatory") String titel,
         @NotBlank(message = "Category is mandatory") String category,
         @NotBlank(message = "Description is mandatory") String description,
         @NotBlank(message = "Recurring is mandatory") String recurring,
         @NotNull(message = "the price should not be null") float price, boolean trial) {
      this.plan_id = plan_id;
      this.titel = titel;
      this.category = category;
      this.description = description;
      this.recurring = recurring;
      this.price = price;
      this.trial = trial;
   }

    @NonNull
    private Long plan_id;

    @NotBlank(message = "Titel is mandatory")
    private String titel;
    
    @NotBlank(message = "Category is mandatory")
    private String category;

    @NotBlank(message = "Description is mandatory")
    private String description;
    
    @NotBlank(message = "Recurring is mandatory")
    private String recurring;
    
    @NotNull(message = "the price should not be null")
    private float price;

    private boolean trial;


}
