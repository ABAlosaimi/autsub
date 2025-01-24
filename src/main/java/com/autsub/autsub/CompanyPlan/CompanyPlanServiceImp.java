package com.autsub.autsub.CompanyPlan;

import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.autsub.autsub.Company.Company;
import com.autsub.autsub.Company.CompanyRepo;
import com.autsub.autsub.CompanyPlan.Dto.PlanRequestDto;
import com.autsub.autsub.CompanyPlan.Dto.PlanResponseDto;
import com.autsub.autsub.Exception.BadRequestException;

public class CompanyPlanServiceImp implements CompanyPlanService {

    private final CompanyPlanRepo companyPlanRepo;
    private final CompanyRepo companyRepo;

    public CompanyPlanServiceImp(CompanyPlanRepo companyPlanRepo, CompanyRepo companyRepo) {
        this.companyPlanRepo = companyPlanRepo;
        this.companyRepo = companyRepo;
    }

    @Override
    public PlanResponseDto createCompanyPlan(PlanRequestDto planRequestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Company> iscompanyActive = companyRepo.findByName(authentication.getCredentials().toString());

        if (!iscompanyActive.isPresent() || iscompanyActive.get().getActive() == false) {
            throw new BadRequestException("Company not found or your account is not active more");
        }

        CompanyPlan companyPlan = new CompanyPlan(
            planRequestDto.getTitel(),
            planRequestDto.getCategory(),
            planRequestDto.getDescription(),
            planRequestDto.getRecurring(),
            planRequestDto.getPrice(),
            planRequestDto.getTrial()
        );

        CompanyPlan newCompanyPlan = companyPlanRepo.save(companyPlan);

        return new PlanResponseDto(newCompanyPlan.getId());

    }

    
    
}
