package com.autsub.autsub.CompanyPlan;

import java.io.IOException;
import java.util.List;

import com.autsub.autsub.CompanyPlan.Dto.PlanRequestDto;
import com.autsub.autsub.CompanyPlan.Dto.PlanResponseDto;

public interface CompanyPlanService {
 PlanResponseDto createCompanyPlan(PlanRequestDto planRequestDto) throws IOException;
 void updatePlanData(PlanRequestDto planRequestDto, Long planID) throws IOException;
 void providOffer(Long planId, int offerPrice) throws Exception;
 void deletePlan(Long planId) throws Exception;
 List<CompanyPlan> getcompanyPlans() throws Exception;
}
