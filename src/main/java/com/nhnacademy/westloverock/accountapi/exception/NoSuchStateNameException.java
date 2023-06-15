package com.nhnacademy.westloverock.accountapi.exception;

public class NoSuchStateNameException extends RuntimeException {
    public NoSuchStateNameException(String state) {
        super(state + ": 해당 상태는 존재하지 않습니다");
    }
}
