package com.autsub.autsub.Company;

import com.autsub.autsub.Company.Dto.LoginResponseDto;
import com.autsub.autsub.Company.Dto.RigterRequestDto;
import com.autsub.autsub.Company.Dto.RigterResponse;

public interface CompnayService {
    RigterResponse registerCompany(RigterRequestDto rigterRequestDto) throws Exception;
    LoginResponseDto Companylogin(RigterRequestDto rigterRequestDto) throws Exception;

}
