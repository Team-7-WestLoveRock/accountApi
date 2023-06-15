package com.nhnacademy.westloverock.accountapi.domain;

import com.nhnacademy.westloverock.accountapi.exception.NoSuchStateNameException;
import com.nhnacademy.westloverock.accountapi.exception.NoSuchStateValueException;
import lombok.Getter;

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
        try {
            return Arrays.stream(State.values())
                    .filter(s -> s.getName().equals(name))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchStateNameException(name));
        } catch (NoSuchStateNameException e) {
            return State.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e2) {
            throw new NoSuchStateValueException(name);
        }
    }

    public static boolean matches(String name) {
        return Arrays.stream(State.values())
                .anyMatch(s -> s.getName().equals(name))
                || matchesEnumValue(name);
    }

    public static Boolean matchesEnumValue(String name) {
        try {
            State.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException i) {
            return false;
        }
        return true;
    }
}