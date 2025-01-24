package com.autsub.autsub.CompanyPlan.Dto;

import lombok.NonNull;

public class PlanResponseDto {
    
    @NonNull
     private Long plan_id;

     public PlanResponseDto(Long plan_id) {
        this.plan_id = plan_id;
     }

}
