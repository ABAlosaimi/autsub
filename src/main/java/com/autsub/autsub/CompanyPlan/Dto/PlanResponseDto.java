package com.autsub.autsub.CompanyPlan.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PlanResponseDto {
    
    @NonNull
     private Long plan_id;

     public PlanResponseDto(Long plan_id) {
        this.plan_id = plan_id;
     }

}
