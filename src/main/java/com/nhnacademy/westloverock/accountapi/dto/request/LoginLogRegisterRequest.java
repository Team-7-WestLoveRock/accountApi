package com.nhnacademy.westloverock.accountapi.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class LoginLogRegisterRequest {
    @NotNull
    private String ipAddress;
}
