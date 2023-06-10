package com.nhnacademy.westloverock.accountapi.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ProfileImagePathRequest {
    @NotNull
    private String imagePath;
}
