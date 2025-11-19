package com.autsub.autsub.PlanStatistics.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubReq {
    
    @NotNull(message = "Plan ID cannot be null")
    Long planId;

}
