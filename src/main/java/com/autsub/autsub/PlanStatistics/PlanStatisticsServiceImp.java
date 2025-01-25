package com.autsub.autsub.PlanStatistics;

import java.io.IOException;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.autsub.autsub.Company.Company;
import com.autsub.autsub.Company.CompanyRepo;
import com.autsub.autsub.CompanyPlan.CompanyPlan;
import com.autsub.autsub.CompanyPlan.CompanyPlanRepo;
import com.autsub.autsub.Exception.UnauthorizedException;
import com.autsub.autsub.PlanStatistics.Dto.StaticPlansDto;

@Service
public class PlanStatisticsServiceImp implements PlanStatisticsService{

    private final CompanyPlanRepo companyPlanRepo;
    private final CompanyRepo companyRepo;

    public PlanStatisticsServiceImp(CompanyPlanRepo companyPlanRepo, CompanyRepo companyRepo) {
        this.companyPlanRepo = companyPlanRepo;
        this.companyRepo = companyRepo;
    }

    @Override
    public void newSubscription(Long planId, String companyName) throws IOException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new UnauthorizedException();
        }

        Optional<Company> isCompanyActive = companyRepo.findByName(companyName);

        if (!isCompanyActive.isPresent() || isCompanyActive.get().getActive() == false) {
            throw new BadRequestException("Company is not active or not found");
        }

        Optional<CompanyPlan> companyPlan = companyPlanRepo.findById(planId);

        if (!companyPlan.isPresent()) {
            throw new BadRequestException("Plan not found");
        }

        companyPlanRepo.updateCompanyPlanSubscription(planId);
        
    }


    @Override
    public void cancelationOfsubscription(Long planId, String companyName) throws BadRequestException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
          throw new UnauthorizedException();
        }

        Optional<Company> isCompanyActive = companyRepo.findByName(companyName);

        if(!isCompanyActive.isPresent() || isCompanyActive.get().getActive() == false){
            throw new BadRequestException("the company account is no more active or not found");
        }

        Optional<CompanyPlan> isCompayPlanExists = companyPlanRepo.findById(planId);

        if (!isCompayPlanExists.isPresent()) {
            throw new BadRequestException("the Plan deleted or the id is not correct");
        }

        companyPlanRepo.updateCompanyPlanCancelation(planId);

    }


    @Override
    public void StumbledPlan(Long planId, String companyName, String stumbleReason) throws BadRequestException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
            throw new UnauthorizedException();
        }

        Optional<Company> isCompanyActive = companyRepo.findByName(companyName);

        if(!isCompanyActive.isPresent() || isCompanyActive.get().getActive() == false){
            throw new BadRequestException("the company account is no more active or not found");
        }

        Optional<CompanyPlan> isCompayPlanExists = companyPlanRepo.findById(planId);

        if (!isCompayPlanExists.isPresent()) {
            throw new BadRequestException("the Plan deleted or the id is not correct");
        }

        companyPlanRepo.updateCompanyPlanStumbledSubscription(planId,stumbleReason);
    }

     
    @Override
    public void insertStaticCompanyPlan(StaticPlansDto staticPlansDto) throws IOException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
            throw new UnauthorizedException();
        }

        Optional<Company> isCompanyActive = companyRepo.findByName(authentication.getCredentials().toString());

        if(!isCompanyActive.isPresent() || isCompanyActive.get().getActive() == false){
            throw new BadRequestException("the company account is no more active or not found");
        }

        String[] planTitles = staticPlansDto.getTitel();
        String[] planCategories = staticPlansDto.getCategory();
        String[] planDescriptions = staticPlansDto.getDescription();
        String[] planRecurrings = staticPlansDto.getRecurring();
        int[] planPrices = staticPlansDto.getPrice();
        boolean[] planTrials = staticPlansDto.getTrial();


        int insertCounter = 0; 
        while (
            staticPlansDto.getTitel().length > insertCounter &&
            staticPlansDto.getCategory().length > insertCounter &&
            staticPlansDto.getDescription().length > insertCounter &&
            staticPlansDto.getPrice().length > insertCounter 
           ) {

        for (int j = 0; j<100; j++){

            companyPlanRepo.insertCompanyPlan(planTitles[j],
             planCategories[j],
             planDescriptions[j],
             planRecurrings[j],
             planPrices[j], 
             planTrials[j]);

            insertCounter++;
        }

      }

    }
    
 }
