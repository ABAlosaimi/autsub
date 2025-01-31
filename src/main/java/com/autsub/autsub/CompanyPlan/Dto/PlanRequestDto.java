package com.autsub.autsub.CompanyPlan.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class PlanRequestDto {

    public PlanRequestDto(){}
    
    @NotBlank(message = "Titel is mandatory")
    private String titel;
    
    @NotBlank(message = "Category is mandatory")
    private String category;

    @NotBlank(message = "Description is mandatory")
    private String description;
    
    private String recurring;
    
    @NotNull(message = "the price shoould not be null")
    private int price;

    private boolean trial;


    public boolean getTrial() {
        return trial;
    }


    public void setTitel(String titel) {
        this.titel = titel;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setRecurring(String recurring) {
        this.recurring = recurring;
    }


    public void setPrice(int price) {
        this.price = price;
    }


    public void setTrial(boolean trial) {
        this.trial = trial;
    }


    public String getTitel() {
        return titel;
    }


    public String getCategory() {
        return category;
    }


    public String getDescription() {
        return description;
    }


    public String getRecurring() {
        return recurring;
    }


    public int getPrice() {
        return price;
    }


    
}
