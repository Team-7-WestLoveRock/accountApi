package com.nhnacademy.westloverock.accountapi.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Generated
public class LoginLogRegisterRequest {
    @NotNull
    private String ipAddress;
}
