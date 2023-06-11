package com.nhnacademy.westloverock.accountapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
@Setter
public class AccountUpdateDto {
    private String userId;
    private String password;
    private String name;
    private String nickname;
    private String email;
    private String phoneNumber;
}
