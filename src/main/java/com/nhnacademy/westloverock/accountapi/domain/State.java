package com.nhnacademy.westloverock.accountapi.domain;

import lombok.Getter;

@Getter
public enum State {

    // ToDo 맛이 갈 것 같아
    ACTIVE("active"),
    WITHDRAWAL("withdrawal"),
    DORMANCY("dormancy");
    private String state;

    State(String state) {
        this.state = state;
    }
}
