package com.autsub.autsub.PlanStatistics;

import com.autsub.autsub.PlanStatistics.Dto.StaticPlansDto;

public interface PlanStatisticsService {
   void newSubscription(Long planId, String companyName) throws Exception;
   void cancelationOfsubscription(Long planId, String companyName) throws Exception;
   void StumbledPlan(Long planId, String companyName, String stumbleReasone) throws Exception;
   void insertStaticCompanyPlan(StaticPlansDto staticPlansDto) throws Exception;
} 
