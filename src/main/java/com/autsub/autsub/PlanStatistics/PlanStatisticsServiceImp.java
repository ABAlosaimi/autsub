package com.autsub.autsub.PlanStatistics;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.autsub.autsub.Company.Company;
import com.autsub.autsub.Company.CompanyRepo;
import com.autsub.autsub.CompanyPlan.CompanyPlan;
import com.autsub.autsub.CompanyPlan.CompanyPlanRepo;
import com.autsub.autsub.Exception.NotActiveAccountException;
import com.autsub.autsub.PlanStatistics.Dto.PlansStatisticsResposeDto;
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
    public void newSubscription(Long planId) throws IOException{
         String companyName = authrnticationCheck();

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
    public void cancelationOfsubscription(Long planId) throws IOException{
         String companyName = authrnticationCheck();

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
    public void stumbledPlan(Long planId, String stumbleReason) throws IOException{
        String companyName = authrnticationCheck();
        
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
         String companyName = authrnticationCheck();

         Optional<Company> isCompanyActive = companyRepo.findByName(companyName);

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
      
            if (staticPlansDto.getTitel().length <= 200) {
                
              for (int j = 0; j<staticPlansDto.getTitel().length/2; j++){

              companyPlanRepo.insertCompanyPlan(planTitles[j],
                 planCategories[j],
                 planDescriptions[j],
                 planRecurrings[j],
                 planPrices[j], 
                 planTrials[j]);

                 insertCounter++;
            }
        }else{
            for (int j = 0; j<staticPlansDto.getTitel().length/10; j++){

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


    @Override
    public List<CompanyPlan> getcompanyPlans() throws IOException{
     String companyName = authrnticationCheck();

     Optional<Company> isCompanyActive = companyRepo.findByName(companyName);

     if (!isCompanyActive.isPresent() || isCompanyActive.get().getActive() == false) {
          throw new NotActiveAccountException();
        }

     Optional<List<CompanyPlan>> companyPlans = companyPlanRepo.findAllByComoanyName(companyName);

        if(!companyPlans.isPresent()){
            throw new BadRequestException("the plan is not exists");
         }

       return companyPlans.get(); 
    }
    

    @Override
    public PlansStatisticsResposeDto getPlansStatistics() throws IOException {
       String companyName = authrnticationCheck();
       
        Optional<Company> isCompanyActive = companyRepo.findByName(companyName);
   
        if (!isCompanyActive.isPresent() || isCompanyActive.get().getActive() == false) {
             throw new NotActiveAccountException();
           }

          Optional<List<CompanyPlan>> companyPlans =  companyPlanRepo.findAllByComoanyName(companyName);

          if (!companyPlans.isPresent()) {
            throw new BadRequestException("theres no plans"); 
          }

          List<CompanyPlan> certnCompanyPlans = companyPlans.get();

          PlansStatisticsResposeDto plansStatisticsResposeDto = new PlansStatisticsResposeDto();
     
          for(int i =0; i<certnCompanyPlans.size(); i++){
            plansStatisticsResposeDto.setSubscription(certnCompanyPlans.get(i).getSubscriptions());
            plansStatisticsResposeDto.setCancelation(certnCompanyPlans.get(i).getCancelation());
            plansStatisticsResposeDto.setStumbled_subscription(certnCompanyPlans.get(i).getStumbled_subscription());
            plansStatisticsResposeDto.setStumbleReason(certnCompanyPlans.get(i).getStumbleReason());
          }

        
        return plansStatisticsResposeDto;
    }


    @Override
    public CompanyPlan getCompanyPlan(Long planId) throws IOException {
        String companyName = authrnticationCheck();
   
        Optional<Company> isCompanyActive = companyRepo.findByName(companyName);
   
        if (!isCompanyActive.isPresent() || isCompanyActive.get().getActive() == false) {
             throw new NotActiveAccountException();
           }

        return companyPlanRepo.findById(planId).get();

    }

    private String authrnticationCheck() throws IOException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
            throw new BadRequestException("you are not authorized to do the action");
        }
   
        return authentication.getCredentials().toString();
    }

    
 }
