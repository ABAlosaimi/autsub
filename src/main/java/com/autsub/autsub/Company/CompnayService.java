package com.autsub.autsub.Company;

import java.io.IOException;

import com.autsub.autsub.Company.Dto.LoginRequestDto;
import com.autsub.autsub.Company.Dto.LoginResponseDto;
import com.autsub.autsub.Company.Dto.PasswordRestRequest;
import com.autsub.autsub.Company.Dto.RigterRequestDto;
import com.autsub.autsub.Company.Dto.RigterResponse;
import com.autsub.autsub.Company.Dto.UpdateCompanyDataDto;

public interface CompnayService {
    RigterResponse registerCompany(RigterRequestDto rigterRequestDto) throws Exception;
    LoginResponseDto Companylogin(LoginRequestDto loginRequestDto) throws Exception;
    void emailAndaddressUpdate(UpdateCompanyDataDto updateCompanyDataDto) throws Exception;
    void updateCompnayPassword(PasswordRestRequest passwordRestRequest) throws IOException;
    void deleteCompany() throws IOException;

}
