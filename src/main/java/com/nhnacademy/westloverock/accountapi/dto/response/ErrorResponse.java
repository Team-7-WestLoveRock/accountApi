package com.nhnacademy.westloverock.accountapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class ErrorResponse {
    private final String title;
    private final int status;
    private final LocalDateTime timestamp;
}
