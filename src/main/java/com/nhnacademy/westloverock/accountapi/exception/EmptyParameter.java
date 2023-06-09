package com.nhnacademy.westloverock.accountapi.exception;

import org.springframework.http.HttpStatus;

public class EmptyParameter extends ApiException {
    public EmptyParameter(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
