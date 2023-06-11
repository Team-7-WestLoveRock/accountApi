package com.nhnacademy.westloverock.accountapi.dto.response;

import java.time.LocalDateTime;

public interface LoginLogDto {
    LocalDateTime getLoginDate();
    String getIpAddress();

}
