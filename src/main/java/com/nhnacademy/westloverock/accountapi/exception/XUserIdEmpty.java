package com.nhnacademy.westloverock.accountapi.exception;

import org.springframework.http.HttpStatus;

public class XUserIdEmpty extends ApiException {
    public XUserIdEmpty(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
