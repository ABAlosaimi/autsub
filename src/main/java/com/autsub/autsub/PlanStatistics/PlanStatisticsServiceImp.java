package com.autsub.autsub.PlanStatistics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class PlanStatisticsServiceImp implements PlanStatisticsService {

    private final CompanyPlanRepo companyPlanRepo;
    private final CompanyRepo companyRepo;

    public PlanStatisticsServiceImp(CompanyPlanRepo companyPlanRepo, CompanyRepo companyRepo) {
        this.companyPlanRepo = companyPlanRepo;
        this.companyRepo = companyRepo;
    }

    @Override
    public void newSubscription(Long planId) throws IOException{
         String companyName = extractPrincipal();

         Optional<Company> isCompanyActive = companyRepo.findByName(companyName);

        if (!isCompanyActive.isPresent() || isCompanyActive.get().getActive() == false) {
            throw new BadRequestException("Company is not active or not found");
        }

        if (planId != null){
             Optional<CompanyPlan> companyPlan = companyPlanRepo.findById(planId);

          
            if (!companyPlan.isPresent()) {
                throw new BadRequestException("Plan not found");
            }
        }

        companyPlanRepo.updateCompanyPlanSubscription(planId);  
    }


    @Override
    public void cancelationOfsubscription(Long planId) throws IOException{
         String companyName = extractPrincipal();

        Optional<Company> isCompanyActive = companyRepo.findByName(companyName);

        if(!isCompanyActive.isPresent() || isCompanyActive.get().getActive() == false){
            throw new BadRequestException("the company account is no more active or not found");
        }

        if (planId != null){
            Optional<CompanyPlan> isCompayPlanExists = companyPlanRepo.findById(planId);

            if (!isCompayPlanExists.isPresent()) {
                throw new BadRequestException("the Plan deleted or the id is not correct");
            }
        }

        companyPlanRepo.updateCompanyPlanCancelation(planId);
    }


    @Override
    public void stumbledPlan(Long planId, String stumbleReason) throws IOException {
        String companyName = extractPrincipal();
        
        Optional<Company> isCompanyActive = companyRepo.findByName(companyName);

        if(!isCompanyActive.isPresent() || isCompanyActive.get().getActive() == false){
            throw new BadRequestException("the company account is no more active or not found");
        }
        
        if (planId != null){
            Optional<CompanyPlan> isCompayPlanExists = companyPlanRepo.findById(planId);

            if (!isCompayPlanExists.isPresent()) {
                throw new BadRequestException("the Plan deleted or the id is not correct");
            }
        }

        companyPlanRepo.updateCompanyPlanStumbledSubscription(planId,stumbleReason);
    }

     
    @Override
    public void insertStaticCompanyPlan(StaticPlansDto staticPlansDto) throws IOException{
         String companyName = extractPrincipal();

         Optional<Company> isCompanyActive = companyRepo.findByName(companyName);

        if(!isCompanyActive.isPresent() || isCompanyActive.get().getActive() == false){
            throw new BadRequestException("the company account is no more active or not found");
        }

        Company company = isCompanyActive.get();
 
        if (staticPlansDto.getPlans().size() > 1000) {
             throw new BadRequestException("Cannot insert more than 1000 plans at once");
        }

         // Convert to entities
         List<CompanyPlan> plansToSave = new ArrayList<>();

        for (Map.Entry<String, Map<String, String>> entry : staticPlansDto.getPlans().entrySet()) {
          String title = entry.getKey();
          Map<String, String> details = entry.getValue();

          CompanyPlan plan = new CompanyPlan(
              title,
              details.get("category"),
              details.get("description"),
              details.get("recurring"),
              Float.parseFloat(details.get("price")),
              Boolean.parseBoolean(details.getOrDefault("trial", "false")),
              company
                );

             plansToSave.add(plan);
            }

      // Batch insert - Spring Data JPA handles batching based on configuration
      companyPlanRepo.saveAll(plansToSave);
    }

    @Override
    public List<CompanyPlan> getcompanyPlans() throws IOException{
     String companyName = extractPrincipal();

     Optional<Company> isCompanyActive = companyRepo.findByName(companyName);

       if (!isCompanyActive.isPresent() || isCompanyActive.get().getActive() == false) {
          throw new NotActiveAccountException();
        }

     Optional<List<CompanyPlan>> companyPlans = companyPlanRepo.findAllByCompanyName(isCompanyActive.get().getName());

        if(!companyPlans.isPresent()){
            throw new BadRequestException("there is no plans for this company currently");
         }

       return companyPlans.get(); 
    }
    

    @Override
    public PlansStatisticsResposeDto getPlansStatistics() throws IOException {
       String companyName = extractPrincipal();
       
        Optional<Company> isCompanyActive = companyRepo.findByName(companyName);
   
        if (!isCompanyActive.isPresent() || isCompanyActive.get().getActive() == false) {
             throw new NotActiveAccountException();
           }

          Optional<List<CompanyPlan>> companyPlans = companyPlanRepo.findAllByCompanyName(isCompanyActive.get().getName());

          if (!companyPlans.isPresent()) {
            throw new BadRequestException("there is no plans for this company currently"); 
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
        String companyName = extractPrincipal();
   
        Optional<Company> isCompanyActive = companyRepo.findByName(companyName);
   
        if (!isCompanyActive.isPresent() || isCompanyActive.get().getActive() == false) {
             throw new NotActiveAccountException();
           }

        if (planId != null) {
             return companyPlanRepo.findById(planId).orElse(null);
        }else{
            throw new BadRequestException("plan id cannot be null");
        }
    }

    private String extractPrincipal() throws IOException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
            throw new BadRequestException("you are not authorized to do the action");
        }
   
        return authentication.getPrincipal().toString();
    }


 }
