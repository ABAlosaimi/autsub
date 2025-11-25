package com.autsub.autsub.Company.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class RigterResponse { 

    public RigterResponse(@NotBlank @NotNull String name, @NotBlank @NotNull String email,
      @NotBlank @NotNull String address, @NotBlank @NotNull String industry,
      @NotBlank @NotNull String commercial_Registration_Number, @NotNull boolean active, @NonNull String accessToken) {
    this.name = name;
    this.email = email;
    this.address = address;
    this.industry = industry;
    this.commercial_Registration_Number = commercial_Registration_Number;
    this.active = active;
    this.accessToken = accessToken;
  }

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    private String address;

    @NotBlank
    @NotNull
    private String industry;

    @NotBlank
    @NotNull
    private String commercial_Registration_Number;

    @NotNull
    private boolean active;

    @NonNull
    private String accessToken;


}
