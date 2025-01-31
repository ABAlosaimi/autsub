package com.autsub.autsub.Company.Dto;

import com.autsub.autsub.Annos.ValidatePassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class RigterRequestDto {

    public RigterRequestDto(){

    }

     public RigterRequestDto(
         String name,
             String email,
             String password,
            String address,
             String industry,
            String commercial_Registration_Number) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.industry = industry;
        this.commercial_Registration_Number = commercial_Registration_Number;
    }

     @NotBlank(message = "Name is mandatory")
     private String name;

     @NotBlank(message = "Email is mandatory")
     @Email(message = "Email should be in valid format")
     private String email;

     @NotBlank(message = "Password is mandatory")
     @ValidatePassword
     private String password;

     @NotBlank(message = "Address is mandatory")
     private String address;

     @NotBlank(message = "Industry is mandatory")
     private String industry;

     @NotBlank(message = "Commercial Registration Number is mandatory")
     private String commercial_Registration_Number;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setCommercial_Registration_Number(String commercial_Registration_Number) {
        this.commercial_Registration_Number = commercial_Registration_Number;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getIndustry() {
        return industry;
    }

    public String getCommercial_Registration_Number() {
        return commercial_Registration_Number;
    }


}
