package com.autsub.autsub.Company.Dto;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class LoginResponseDto {

    public LoginResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    @NonNull
    private String accessToken;
}
