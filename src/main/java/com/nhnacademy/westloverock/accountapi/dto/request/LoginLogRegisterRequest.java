package com.nhnacademy.westloverock.accountapi.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class LoginLogRegisterRequest {
    @NotNull
    private String ipAddress;
}
