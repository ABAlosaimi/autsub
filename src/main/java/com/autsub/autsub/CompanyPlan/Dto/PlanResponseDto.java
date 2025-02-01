package com.autsub.autsub.CompanyPlan.Dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class PlanResponseDto {
    
    @NonNull
     private Long plan_id;

     public PlanResponseDto(Long plan_id) {
        this.plan_id = plan_id;
     }

}
