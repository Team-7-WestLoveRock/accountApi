package com.nhnacademy.westloverock.accountapi;

import com.nhnacademy.westloverock.accountapi.domain.State;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class StateTest {

    @Test
    void test1() {
        String withdrawal = "withdrawal";

        State state = State.valueOf("WITHDRAWAL");
//
        System.out.println(state);
        Arrays.stream(State.values()).forEach(s -> System.out.println(s.getName()));

//        System.out.println(State.ACTIVE.getName());
//        System.out.println(State.va);
    }
}
