package com.nhnacademy.westloverock.accountapi.exception;

public class NoSuchStateValueException extends RuntimeException {
    public NoSuchStateValueException(String name) {
        super(name + ": 해당 상태값은 존재하지않습니다");
    }
}
