package com.autsub.autsub.Company.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateIdentityOfCompnay {

    public UpdateIdentityOfCompnay(){}

    @NotBlank(message = "the name is required")
    private String name; 
    
    @NotBlank(message = "The industry should be addressed")
    @Pattern(regexp = "Finance|Healthcare|Retail|Manufacturing|Education", message = "the new industry should be in the set of the cuntry's sectors")
    private String industry;

    @NotBlank(message = "the commercial number should be provided")
    @Size(min = 10,max = 10, message = "the commercial number isn't complete")
    private String commercial_Registration_Number;
    
}
