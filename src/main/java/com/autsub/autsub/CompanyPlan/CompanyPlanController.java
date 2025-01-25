package com.autsub.autsub.CompanyPlan;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autsub.autsub.CompanyPlan.Dto.PlanRequestDto;


@RestController
@RequestMapping("/companyPlan")
public class CompanyPlanController {

    private final CompanyPlanService companyPlanService;

    public CompanyPlanController(CompanyPlanService companyPlanService) {
        this.companyPlanService = companyPlanService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCompanyPlan(PlanRequestDto planRequestDto) throws IOException {
         companyPlanService.createCompanyPlan(planRequestDto);

         return ResponseEntity.status(201)
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .body(companyPlanService.createCompanyPlan(planRequestDto));
    }


    @PutMapping("/update")
    public ResponseEntity<Object> updatePlanData(PlanRequestDto planRequestDto) throws IOException {
        companyPlanService.updatePlanData(planRequestDto);

        return ResponseEntity.status(200).build();
                             
    }

    @PutMapping("/offer")
    public ResponseEntity<Object> providOffer(Long planId) {
        companyPlanService.providOffer(planId);

        return ResponseEntity.status(200).build();
                             
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deletePlan(Long planId) {
        companyPlanService.deletePlan(planId);

        return ResponseEntity.status(204).build();
                             
    }

}
