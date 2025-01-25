package com.autsub.autsub.CompanyPlan;

import java.io.IOException;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.autsub.autsub.Company.Company;
import com.autsub.autsub.Company.CompanyRepo;
import com.autsub.autsub.CompanyPlan.Dto.PlanRequestDto;
import com.autsub.autsub.CompanyPlan.Dto.PlanResponseDto;
import com.autsub.autsub.Exception.BadRequestException;
import com.autsub.autsub.Exception.NotActiveAccountException;

@Service
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
            throw new NotActiveAccountException();
        }

        CompanyPlan companyPlan = new CompanyPlan(
            planRequestDto.getTitel(),
            planRequestDto.getCategory(),
            planRequestDto.getDescription(),
            planRequestDto.getRecurring(),
            planRequestDto.getPrice(),
            planRequestDto.getTrial(),
            iscompanyActive.get()
        );

        CompanyPlan newCompanyPlan = companyPlanRepo.save(companyPlan);

        return new PlanResponseDto(newCompanyPlan.getId());

    }

    @Override
    public void updatePlanData(PlanRequestDto planRequestDto) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Company> iscompanyActive = companyRepo.findByName(authentication.getCredentials().toString());

        if (!iscompanyActive.isPresent() || iscompanyActive.get().getActive() == false) {
            throw new NotActiveAccountException();
        }

        Company company = iscompanyActive.get();

        Optional<CompanyPlan> companyPlan = companyPlanRepo.findByCompany_name(company.getName());

        if (!companyPlan.isPresent()) {
            throw new BadRequestException("Company plan not found");
        }

        CompanyPlan newCompanyPlan = companyPlan.get();

        companyPlanRepo.updateCompanyPlan(  
        newCompanyPlan.getTitel(),
         newCompanyPlan.getCategory(),
          newCompanyPlan.getDescription(),
           newCompanyPlan.getRecurring(),
            newCompanyPlan.getCategory(),
             newCompanyPlan.getTitel());

    }


    @Override
    public void providOffer(Long planId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if(authentication == null){
            throw new BadRequestException("you are not authorized to do this action");
        }

       Optional<CompanyPlan> isPlanExists = companyPlanRepo.findById(planId);

         if(isPlanExists.isEmpty()){
              throw new BadRequestException("plan not found");
         }

        CompanyPlan companyPlan = isPlanExists.get();

        companyPlanRepo.updateCompanyPlanLastOffer(companyPlan.getId());
        
    }


    @Override
    public void deletePlan(Long palnId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new BadRequestException("you are not authorized to do this action");
        }

        companyPlanRepo.deleteById(palnId);
    }
   
 }
