package com.autsub.autsub.Company.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    @NotBlank(message = "the email is mandatory")
    @Email(message = "the email should be in valid format")
     private String email;

     @NotBlank(message = "the password is mandatory")
     private String password;

}
