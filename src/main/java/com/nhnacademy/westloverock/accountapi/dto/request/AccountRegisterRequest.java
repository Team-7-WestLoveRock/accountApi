package com.nhnacademy.westloverock.accountapi.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class AccountRegisterRequest {
    @NotNull
    private String userId;
    @NotNull
    private String password;
    @NotNull
    private String name;
    private String nickname;
    @NotNull
    private String email;
    private String phoneNumber;
}
