package com.autsub.autsub.Company;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.autsub.autsub.AICalls.ChatGPTService;
import com.autsub.autsub.Company.Auth.JWTService;
import com.autsub.autsub.Company.Dto.LoginRequestDto;
import com.autsub.autsub.Company.Dto.LoginResponseDto;
import com.autsub.autsub.Company.Dto.PasswordRestRequest;
import com.autsub.autsub.Company.Dto.RigterRequestDto;
import com.autsub.autsub.Company.Dto.RigterResponse;
import com.autsub.autsub.Company.Dto.UpdateCompanyDataDto;
import com.autsub.autsub.Company.Dto.UpdateIdentityOfCompnay;
import com.autsub.autsub.CompanyPlan.CompanyPlan;
import com.autsub.autsub.CompanyPlan.CompanyPlanRepo;

@Service
public class CompanyServiceImp implements CompnayService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final CompanyRepo companyRepo;
    private final JWTService jwtService;
    private final CompanyPlanRepo companyPlanRepo;
    private final ChatGPTService chatGPTService;

     CompanyServiceImp(
      BCryptPasswordEncoder passwordEncoder, 
      CompanyRepo companyRepo,
      JWTService jwtService, 
      UserDetailsService userDetailsService, 
      CompanyPlanRepo companyPlanRepo, 
      ChatGPTService chatGPTService) {

        this.chatGPTService = chatGPTService;
        this.companyPlanRepo = companyPlanRepo;
        this.passwordEncoder = passwordEncoder;
        this.companyRepo = companyRepo;
        this.jwtService = jwtService;
    }


    @Override
    public RigterResponse registerCompany(RigterRequestDto rigterRequestDto) throws Exception {
       Optional<Company> isCompanyExists = companyRepo.findByEmail(rigterRequestDto.getEmail());

       if (isCompanyExists.isPresent()) {
          throw new BadRequestException("you are already rigsterd, try to login");
       }

       rigterRequestDto.setPassword(passwordEncoder.encode(rigterRequestDto.getPassword()));
      
       Company newCompnay = new Company(
        rigterRequestDto.getName(), 
        rigterRequestDto.getPassword(),
        rigterRequestDto.getEmail(),
        rigterRequestDto.getAddress(),
        rigterRequestDto.getIndustry(),
        rigterRequestDto.getCommercial_Registration_Number()
          );

          companyRepo.save(newCompnay);

        String accessToken = jwtService.generateToken(newCompnay); 

        return new RigterResponse(accessToken);
    }
   

    @Override
    public LoginResponseDto Companylogin(LoginRequestDto loginRequestDto) throws Exception {
        Optional<Company> iscompanyExists = companyRepo.findByEmail(loginRequestDto.getEmail());

        if (iscompanyExists.isEmpty()) {
            throw new BadRequestException("something went wrong, duble check your email or password");
        }

        Company company = iscompanyExists.get();

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), company.getPassword())) {
            throw new BadRequestException("something went wrong, duble check your email or password");
        }

        String accessToken = jwtService.generateToken(company);

        // Optional<List<CompanyPlan>> isCompanyPlansExsits = companyPlanRepo.findAllByCompanyName(company);

        // if (isCompanyPlansExsits.isEmpty()) {
        //     return new LoginResponseDto(accessToken);
        // }else if (company.getActive() != false){
        //  chatGPTService.sendPlanDataToChatGPT(isCompanyPlansExsits.get());
        // }

        return new LoginResponseDto(accessToken);
    }


    @Override
    public void emailAndaddressUpdate(UpdateCompanyDataDto updateCompanyDataDto) throws Exception {
        companyRepo.updateCompanyEmailAndAddress(updateCompanyDataDto.getEmail(), updateCompanyDataDto.getAddress(), updateCompanyDataDto.getName());
    }


     @Override
     public void updateCompnayPassword(PasswordRestRequest passwordRestRequest) throws IOException{
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       Optional<Company> isCompanyExists = companyRepo.findByName(authentication.getName());

         if (isCompanyExists.isEmpty()) {
              throw new BadRequestException("the account you are trying to update is not found");
         }

         String newPassword = passwordRestRequest.getNewPassword();
         Company company = isCompanyExists.get();

         if (!passwordEncoder.matches(passwordRestRequest.getOldPassword(), company.getPassword())) {
             throw new BadRequestException("the password you entered is not correct");
         }

         company.setPassword(passwordEncoder.encode(newPassword));

         companyRepo.save(company);

     }

     @Override
     public void updateCompanyidentifyDat(UpdateIdentityOfCompnay updateIdentityOfCompnay){
        companyRepo.updateCompanyidentifyDat(updateIdentityOfCompnay.getName(), updateIdentityOfCompnay.getIndustry(), updateIdentityOfCompnay.getCommercial_Registration_Number());
     }



     public void deleteCompany() throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Company> isCompanyExists = companyRepo.findByName(authentication.getName());

        if (isCompanyExists.isEmpty()) {
            throw new BadRequestException("the account you are trying to delete is not found");
        }

        companyRepo.delete(isCompanyExists.get());
     }
      
}
