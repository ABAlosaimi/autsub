package com.autsub.autsub.Company;

import java.io.IOException;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.autsub.autsub.Company.Auth.JWTService;
import com.autsub.autsub.Company.Dto.LoginRequestDto;
import com.autsub.autsub.Company.Dto.LoginResponseDto;
import com.autsub.autsub.Company.Dto.PasswordRestRequest;
import com.autsub.autsub.Company.Dto.RigterRequestDto;
import com.autsub.autsub.Company.Dto.RigterResponse;
import com.autsub.autsub.Company.Dto.UpdateCompanyDataDto;
import com.autsub.autsub.Company.Dto.UpdateIdentityOfCompnay;

@Service
public class CompanyServiceImp implements CompnayService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final CompanyRepo companyRepo;
    private final JWTService jwtService;
    

     CompanyServiceImp(
      BCryptPasswordEncoder passwordEncoder, 
      CompanyRepo companyRepo,
      JWTService jwtService, 
      UserDetailsService userDetailsService) {
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

        RigterResponse response = new RigterResponse(newCompnay.getName(),
                                                      newCompnay.getEmail(),
                                                      newCompnay.getAddress(),
                                                      newCompnay.getIndustry(), 
                                                      newCompnay.getCommercial_Registration_Number(), 
                                                      newCompnay.getActive(), 
                                                      accessToken);

        return response;
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

        LoginResponseDto responseDto = new LoginResponseDto(company.getName(), 
                                                                company.getEmail(),
                                                                company.getAddress(),
                                                                company.getIndustry(),
                                                                company.getCommercial_Registration_Number(),
                                                                company.getActive(), accessToken);
        
        return responseDto;
    }


    @Override
    public void emailAndaddressUpdate(UpdateCompanyDataDto updateCompanyDataDto) throws Exception {
        companyRepo.updateCompanyEmailAndAddress(updateCompanyDataDto.getEmail(), updateCompanyDataDto.getAddress(), updateCompanyDataDto.getName());
    }


     @Override // typo in the param PasswordRestRequest -> PasswordResetRequest
     public void updateCompnayPassword(PasswordRestRequest passwordRestRequest) throws IOException{ // need to be updated in terms of the auth, how someone forgot their pass and provide auth data ? 
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

     @Override // typo updateCompanyidentifyDat -> updateCompanyidentityData
     public void updateCompanyidentifyDat(UpdateIdentityOfCompnay updateIdentityOfCompnay){
        companyRepo.updateCompanyidentifyDat(updateIdentityOfCompnay.getName(), updateIdentityOfCompnay.getIndustry(), updateIdentityOfCompnay.getCommercial_Registration_Number());
     }


     public void deleteCompany() throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Company> isCompanyExists = companyRepo.findByName(authentication.getName());

        if (isCompanyExists.isEmpty()) {
            throw new BadRequestException("the account you are trying to delete is not found");
        }

        Company company = isCompanyExists.get();
        
        if (company != null){
            companyRepo.delete(company);
        }
        
     }
      
}