package com.autsub.autsub.Company.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCompanyDataDto {
 
     @NotBlank(message = "Email is mandatory")
     @Email(message = "Email should be in valid format")
     private String email;

     @NotBlank(message = "Address is mandatory")
     private String address;

}
