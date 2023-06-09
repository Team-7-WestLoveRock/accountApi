package com.nhnacademy.westloverock.accountapi.exception;

import org.springframework.http.HttpStatus;

public class CsvException extends ApiException {

    public CsvException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
