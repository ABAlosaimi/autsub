package com.autsub.autsub.Company.Dto;

import com.autsub.autsub.Annos.ValidatePassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RigterRequestDto {
    
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
     private String Commercial_Registration_Number;

}
