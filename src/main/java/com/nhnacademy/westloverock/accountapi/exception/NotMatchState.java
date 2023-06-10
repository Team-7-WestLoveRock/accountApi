package com.nhnacademy.westloverock.accountapi.exception;

import org.springframework.http.HttpStatus;

public class NotMatchState extends ApiException {
    public NotMatchState(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
