package com.autsub.autsub.CompanyPlan;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.autsub.autsub.CompanyPlan.Dto.PlanRequestDto;
import com.autsub.autsub.CompanyPlan.Dto.PlanResponseDto;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/companyPlan")
public class CompanyPlanController {

    private final CompanyPlanService companyPlanService;

    public CompanyPlanController(CompanyPlanService companyPlanService) {
        this.companyPlanService = companyPlanService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCompanyPlan(@Valid @RequestBody PlanRequestDto planRequestDto) throws Exception {
        PlanResponseDto response = companyPlanService.createCompanyPlan(planRequestDto);

         return ResponseEntity.status(201)
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .body(response);
    }


    @PutMapping("/update")
    public ResponseEntity<Object> updatePlanData(@Valid @RequestBody PlanRequestDto planRequestDto, @RequestBody Long planID) throws Exception {
        companyPlanService.updatePlanData(planRequestDto, planID);

        return ResponseEntity.status(200).build();
                             
    }

    @PutMapping("/offer")
    public ResponseEntity<Object> providOffer(@RequestBody Long planId, @RequestBody int offerPrice) throws Exception {
        companyPlanService.providOffer(planId, offerPrice);

        return ResponseEntity.status(200).build();
                             
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deletePlan(@RequestBody Long planId) throws Exception {
        companyPlanService.deletePlan(planId);

        return ResponseEntity.status(204).build();
                             
    }


}
