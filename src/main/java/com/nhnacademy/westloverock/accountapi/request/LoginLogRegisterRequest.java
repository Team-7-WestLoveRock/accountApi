package com.nhnacademy.westloverock.accountapi.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class LoginLogRegisterRequest {
    @NotNull
    private String ipAddress;
}
