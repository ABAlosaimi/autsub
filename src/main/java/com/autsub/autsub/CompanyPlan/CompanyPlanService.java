package com.autsub.autsub.CompanyPlan;

import java.util.List;
import com.autsub.autsub.CompanyPlan.Dto.PlanRequestDto;
import com.autsub.autsub.CompanyPlan.Dto.PlanResponseDto;

public interface CompanyPlanService {
 PlanResponseDto createCompanyPlan(PlanRequestDto planRequestDto) throws Exception;
 PlanResponseDto updatePlanData(PlanRequestDto planRequestDto, Long planID) throws Exception; 
 void updatePlanLastOfferPrice(Long planId, float offerPrice) throws Exception;   
 void deletePlan(Long planId) throws Exception;
 List<CompanyPlan> getcompanyPlans() throws Exception;
}
