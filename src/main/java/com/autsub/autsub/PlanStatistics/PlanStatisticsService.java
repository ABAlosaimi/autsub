package com.autsub.autsub.PlanStatistics;

import java.util.List;
import com.autsub.autsub.CompanyPlan.CompanyPlan;
import com.autsub.autsub.PlanStatistics.Dto.PlansStatisticsResposeDto;
import com.autsub.autsub.PlanStatistics.Dto.StaticPlansDto;

public interface PlanStatisticsService {
   void newSubscription(Long planId) throws Exception;
   void cancelationOfsubscription(Long planId) throws Exception;
   void stumbledPlan(Long planId, String stumbleReasone) throws Exception;
   void insertStaticCompanyPlan(StaticPlansDto staticPlansDto) throws Exception;
   
   List<CompanyPlan> getcompanyPlans() throws Exception;
   PlansStatisticsResposeDto getPlansStatistics() throws Exception;
   CompanyPlan getCompanyPlan(Long planId) throws Exception;
}
