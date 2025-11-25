package com.autsub.autsub.PlanStatistics.Dto;

import java.util.Map;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StaticPlansDto { 

    @NotNull
    private Map<String, Map<String, String>> plans;

}