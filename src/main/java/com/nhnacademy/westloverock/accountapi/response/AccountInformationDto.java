package com.nhnacademy.westloverock.accountapi.response;

import com.nhnacademy.westloverock.accountapi.entity.Account;

public interface AccountInformationDto {
    String getUserId();
    String getPassword();
    String getEmail();
}
