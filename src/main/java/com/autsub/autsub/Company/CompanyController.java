package com.autsub.autsub.Company;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autsub.autsub.Company.Dto.LoginRequestDto;
import com.autsub.autsub.Company.Dto.PasswordRestRequest;
import com.autsub.autsub.Company.Dto.RigterRequestDto;
import com.autsub.autsub.Company.Dto.RigterResponse;
import com.autsub.autsub.Company.Dto.UpdateCompanyDataDto;
import com.autsub.autsub.Company.Dto.UpdateIdentityOfCompnay;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompnayService compnayService;

    public CompanyController(CompnayService compnayService) {
        this.compnayService = compnayService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Object> registerCompany(@Valid @RequestBody RigterRequestDto rigterRequestDto) throws Exception {
        RigterResponse response = compnayService.registerCompany(rigterRequestDto);
        
        return ResponseEntity.status(201)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(response);
    }   


    @GetMapping("/auth/login")
    public ResponseEntity<Object> loginCompany(@Valid @RequestBody LoginRequestDto loginRequestDto) throws Exception {
        compnayService.Companylogin(loginRequestDto);
        
        return ResponseEntity.status(200)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(compnayService.Companylogin(loginRequestDto));
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateCompany(@Valid @RequestBody UpdateCompanyDataDto updateCompanyDataDto) throws Exception {
        compnayService.emailAndaddressUpdate(updateCompanyDataDto);
        
        return ResponseEntity.status(200).build();
                             
    }

    @PutMapping("/reset-password")
    public ResponseEntity<Object> resetPassword(@Valid @RequestBody PasswordRestRequest passwordRestRequest) throws Exception {
        compnayService.updateCompnayPassword(passwordRestRequest);
        
        return ResponseEntity.status(200).build();                        
    }

    @PutMapping("/update-I")
    public ResponseEntity<Object> updateCompanyIdentityData(@Valid @RequestBody UpdateIdentityOfCompnay updateIdentityOfCompnay){
        compnayService.updateCompanyidentifyDat(updateIdentityOfCompnay);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteCompany() throws IOException {
        compnayService.deleteCompany();
        return ResponseEntity.status(200).build();
    }
   
 }
