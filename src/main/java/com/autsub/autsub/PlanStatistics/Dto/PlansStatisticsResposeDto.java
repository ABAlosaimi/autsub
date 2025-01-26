package com.autsub.autsub.PlanStatistics.Dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PlansStatisticsResposeDto {
    
     private List<Double> subscription;

     private List<Double> cancelation;

     private List<Double> stumbled_subscription;

     private List<String> stumbleReason;

     public void setSubscription(double subscription) {
         this.subscription.add(subscription);
     }

     public void setCancelation(double cancelation) {
         this.cancelation.add(cancelation);
     }

     public void setStumbleReason(String stumbleReason) {
         this.stumbleReason.add(stumbleReason);
     }

     public void setStumbled_subscription(double stumbled_subscription) {
         this.stumbled_subscription.add(stumbled_subscription);
     }


}
