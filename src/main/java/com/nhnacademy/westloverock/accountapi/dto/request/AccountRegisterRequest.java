package com.nhnacademy.westloverock.accountapi.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class AccountRegisterRequest {
    @NotNull
    private String userId;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String email;
    private String nickname;
    private String phoneNumber;
}
