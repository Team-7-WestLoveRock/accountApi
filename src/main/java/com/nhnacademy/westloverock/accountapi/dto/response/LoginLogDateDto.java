package com.nhnacademy.westloverock.accountapi.dto.response;

import lombok.*;


import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class LoginLogDateDto {
    private LocalDateTime loginDate;
}
