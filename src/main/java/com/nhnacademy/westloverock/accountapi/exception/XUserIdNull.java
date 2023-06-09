package com.nhnacademy.westloverock.accountapi.exception;

import org.springframework.http.HttpStatus;

public class XUserIdNull extends ApiException {
    public XUserIdNull(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
