package com.autsub.autsub.PlanStatistics;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autsub.autsub.CompanyPlan.CompanyPlan;
import com.autsub.autsub.PlanStatistics.Dto.PlansStatisticsResposeDto;
import com.autsub.autsub.PlanStatistics.Dto.StaticPlansDto;

import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/statistics")
public class PlanStatisticsController {

    private final PlanStatisticsService planStatisticsService;

    PlanStatisticsController(PlanStatisticsService planStatisticsService){
        this.planStatisticsService = planStatisticsService;
    }


    @PutMapping("/gather-new")
    public ResponseEntity<Object> newSubscription(@NotBlank @RequestBody Long planId) throws Exception{
      planStatisticsService.newSubscription(planId);

      return ResponseEntity.status(200).build();
    }

    @PutMapping("/cancel")
    public ResponseEntity<Object> cancleSubscription(@NotBlank @RequestBody Long planId) throws Exception{
        planStatisticsService.cancelationOfsubscription(planId);

        return ResponseEntity.status(200).build();
    }

    @PutMapping("/stumble")
    public ResponseEntity<Object> stumbleSubscription(@NotBlank @RequestBody Long planId, @NotBlank @RequestBody String stumbleReason) throws Exception{
     planStatisticsService.stumbledPlan(planId, stumbleReason);

     return ResponseEntity.status(200).build();

    }

    @PutMapping("/static")
    public ResponseEntity<Object> insertStaticPlnas(StaticPlansDto staticPlansDto) throws Exception{
        planStatisticsService.insertStaticCompanyPlan(staticPlansDto);

      return ResponseEntity.status(200).build();

    }

    @GetMapping("/allplans")
    public ResponseEntity<List<CompanyPlan>> getCompanyPlans() throws Exception{
        planStatisticsService.getcompanyPlans();

        return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(planStatisticsService.getcompanyPlans());
    }

    @GetMapping("/plansStatiscs")
    public ResponseEntity<PlansStatisticsResposeDto> getPlansStatistics() throws Exception{
        planStatisticsService.getPlansStatistics();

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(planStatisticsService.getPlansStatistics());
    }


    @GetMapping("/plan")
    public ResponseEntity<CompanyPlan> getCompanyPlan(@NotBlank @RequestBody Long planId) throws Exception{
        planStatisticsService.getCompanyPlan(planId);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(planStatisticsService.getCompanyPlan(planId));
    }
    
}
