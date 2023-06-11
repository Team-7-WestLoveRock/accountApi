package com.nhnacademy.westloverock.accountapi.dto.response;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
@Generated
public class AccountUpdateDto {
    private String userId;
    private String password;
    private String name;
    private String nickname;
    private String email;
    private String phoneNumber;
}
