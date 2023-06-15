package com.nhnacademy.westloverock.accountapi.advice;


import com.nhnacademy.westloverock.accountapi.dto.response.AccountInformationDto;
import com.nhnacademy.westloverock.accountapi.exception.ApiException;
import com.nhnacademy.westloverock.accountapi.dto.response.ErrorResponse;
import com.nhnacademy.westloverock.accountapi.exception.ObjectNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class CommonAdvice {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> apiException(ApiException ex) {
        log.error("apiException : {}", ex.getMessage(), ex);

        ErrorResponse error = ErrorResponse.builder()
                .title(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .status(ex.getStatus().value())
                .build();

        return ResponseEntity.status(ex.getStatus()).body(error);

    }

}
