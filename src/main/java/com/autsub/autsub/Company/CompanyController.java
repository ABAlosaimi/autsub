package com.autsub.autsub.Company;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autsub.autsub.Company.Dto.RigterRequestDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {

    CompnayService compnayService;

    public CompanyController(CompnayService compnayService) {
        this.compnayService = compnayService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Object> registerCompany(@Valid @RequestBody RigterRequestDto rigterRequestDto) throws Exception {
        compnayService.registerCompany(rigterRequestDto);
        
        return ResponseEntity.status(201)
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(compnayService.registerCompany(rigterRequestDto));
    }   
    
}
