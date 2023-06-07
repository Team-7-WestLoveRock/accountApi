package com.nhnacademy.westloverock.accountapi.request;

import com.nhnacademy.westloverock.accountapi.domain.State;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class AccountStateRequest {
    @NotNull
    private String state;
}
