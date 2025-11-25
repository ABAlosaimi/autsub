package com.autsub.autsub.CompanyPlan;

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
import jakarta.validation.constraints.NotNull;


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
                                 .body(response);
    }


    @PutMapping("/update")
    public ResponseEntity<Object> updatePlanData(@Valid @RequestBody PlanRequestDto planRequestDto, @RequestBody @NotNull Long planID) throws Exception {
        companyPlanService.updatePlanData(planRequestDto, planID);

        return ResponseEntity.status(200).build();
                             
    }

    @PutMapping("/offer")
    public ResponseEntity<Object> providOffer(@RequestBody @NotNull Long planId, @RequestBody @NotNull int offerPrice) throws Exception {
        companyPlanService.updatePlanLastOfferPrice(planId, offerPrice);

        return ResponseEntity.status(200).build();
                             
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deletePlan(@RequestBody @NotNull Long planId) throws Exception {
        companyPlanService.deletePlan(planId);

        return ResponseEntity.status(204).build();
                             
    }


}
