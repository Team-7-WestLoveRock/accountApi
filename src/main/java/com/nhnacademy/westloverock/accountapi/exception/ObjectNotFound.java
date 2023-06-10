package com.nhnacademy.westloverock.accountapi.exception;

import org.springframework.http.HttpStatus;

public class ObjectNotFound extends ApiException {
    public ObjectNotFound(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
