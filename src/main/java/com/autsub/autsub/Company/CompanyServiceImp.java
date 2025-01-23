package com.autsub.autsub.Company;

import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.autsub.autsub.Company.Auth.JWTService;
import com.autsub.autsub.Company.Dto.LoginResponseDto;
import com.autsub.autsub.Company.Dto.RigterRequestDto;
import com.autsub.autsub.Company.Dto.RigterResponse;

@Service
public class CompanyServiceImp implements CompnayService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final CompanyRepo companyRepo;
    private final JWTService jwtService;
    //private final MailSender mailSender;
    //private final SimpleMailMessage message;

    CompanyServiceImp(BCryptPasswordEncoder passwordEncoder, CompanyRepo companyRepo, JWTService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.companyRepo = companyRepo;
        this.jwtService = jwtService;
       // this.mailSender = mailSender;
        //this.message = message;
    }


    @Override
    public RigterResponse registerCompany(RigterRequestDto rigterRequestDto) throws Exception {
       Optional<Company> company = companyRepo.findByName(rigterRequestDto.getName());

       if (company.isPresent()) {
          throw new BadRequestException("you are already rigsterd, try to login");
       }

       // sendin OTP functionality to company's email
       
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
    public LoginResponseDto Companylogin(RigterRequestDto rigterRequestDto) throws Exception {
        Optional<Company> iscompanyExists = companyRepo.findByName(rigterRequestDto.getName());

        if (iscompanyExists.isEmpty()) {
            throw new BadRequestException("something went wrong, duble check your email or password");
        }

        Company company = iscompanyExists.get();

        if (!passwordEncoder.matches(rigterRequestDto.getPassword(), company.getPassword())) {
            throw new BadRequestException("something went wrong, duble check your email or password");
        }

        String accessToken = jwtService.generateToken(company);

        return new LoginResponseDto(accessToken);
    }


      
}
