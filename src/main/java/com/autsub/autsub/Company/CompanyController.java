package com.autsub.autsub.Company;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {


    @PostMapping("/register")
    public ResponseEntity<Object> registerCompany(@RequestBody Company company) {
        return null;
    }   
    
}
