package com.nhnacademy.westloverock.accountapi.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
@Getter
public enum State {

    ACTIVE("활동"),
    WITHDRAWAL("탈퇴"),
    DORMANCY("휴면");
    private String name;

    State(String name) {
        this.name = name;
    }
    public static State getState(String name) {
        return Arrays.stream(State.values())
                .filter(s -> s.getName().equals(name))
                .findFirst()
                // ToDo custom error
                .orElseThrow(() -> new RuntimeException(""));
    }

    public static boolean matches(String name) {
        return Arrays.stream(State.values())
                .anyMatch(s -> s.getName().equals(name));
    }
}