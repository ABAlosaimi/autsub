package com.autsub.autsub.Company.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class RigterResponse {

  public RigterResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    @NonNull
    private String accessToken;
}
