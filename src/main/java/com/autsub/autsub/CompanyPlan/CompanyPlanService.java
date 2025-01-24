package com.autsub.autsub.CompanyPlan;

import java.io.IOException;

import com.autsub.autsub.CompanyPlan.Dto.PlanRequestDto;
import com.autsub.autsub.CompanyPlan.Dto.PlanResponseDto;

public interface CompanyPlanService {
 PlanResponseDto createCompanyPlan(PlanRequestDto planRequestDto) throws IOException;
}
