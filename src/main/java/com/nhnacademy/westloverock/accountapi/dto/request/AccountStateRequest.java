package com.nhnacademy.westloverock.accountapi.dto.request;

import com.nhnacademy.westloverock.accountapi.domain.State;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class AccountStateRequest {
    @NotNull
    private String state;
}
