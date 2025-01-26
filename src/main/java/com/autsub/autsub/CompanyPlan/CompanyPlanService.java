package com.autsub.autsub.CompanyPlan;

import java.io.IOException;
import java.util.List;

import com.autsub.autsub.CompanyPlan.Dto.PlanRequestDto;
import com.autsub.autsub.CompanyPlan.Dto.PlanResponseDto;

public interface CompanyPlanService {
 PlanResponseDto createCompanyPlan(PlanRequestDto planRequestDto) throws IOException;
 void updatePlanData(PlanRequestDto planRequestDto) throws IOException;
 void providOffer(Long planId, int offerPrice);
 void deletePlan(Long planId);
 List<CompanyPlan> getcompanyPlans() throws Exception;
}
