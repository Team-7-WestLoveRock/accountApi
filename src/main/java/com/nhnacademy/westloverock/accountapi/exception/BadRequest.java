package com.nhnacademy.westloverock.accountapi.exception;

import org.springframework.http.HttpStatus;

public class BadRequest extends ApiException {

    public BadRequest(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
