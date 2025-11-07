package com.autsub.autsub.CompanyPlan;

import java.util.List;
import com.autsub.autsub.CompanyPlan.Dto.PlanRequestDto;
import com.autsub.autsub.CompanyPlan.Dto.PlanResponseDto;

public interface CompanyPlanService {
 PlanResponseDto createCompanyPlan(PlanRequestDto planRequestDto) throws Exception;
 void updatePlanData(PlanRequestDto planRequestDto, Long planID) throws Exception; // should return updated plan data 
 void providOffer(Long planId, float offerPrice) throws Exception; // should return the offer details  
 void deletePlan(Long planId) throws Exception;
 List<CompanyPlan> getcompanyPlans() throws Exception;
}
