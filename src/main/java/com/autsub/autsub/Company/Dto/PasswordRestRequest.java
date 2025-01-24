package com.autsub.autsub.Company.Dto;

import com.autsub.autsub.Annos.ValidatePassword;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordRestRequest {

    @ValidatePassword
    @NotBlank(message = "Password is mandatory")
    @NonNull
    private String password;
}
