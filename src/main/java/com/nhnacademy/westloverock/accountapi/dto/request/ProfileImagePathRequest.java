package com.nhnacademy.westloverock.accountapi.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class ProfileImagePathRequest {
    @NotNull
    private String imagePath;
}
