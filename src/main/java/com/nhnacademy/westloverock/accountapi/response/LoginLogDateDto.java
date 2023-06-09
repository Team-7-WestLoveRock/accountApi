package com.nhnacademy.westloverock.accountapi.response;

import lombok.Builder;
import lombok.Getter;


import java.time.LocalDateTime;

@Builder
@Getter
public class LoginLogDateDto {
    private LocalDateTime loginDate;
}
