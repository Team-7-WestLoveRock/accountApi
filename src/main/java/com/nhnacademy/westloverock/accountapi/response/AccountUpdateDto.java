package com.nhnacademy.westloverock.accountapi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Builder
@Setter
public class AccountUpdateDto {
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
