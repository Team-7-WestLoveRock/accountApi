package com.nhnacademy.westloverock.accountapi.response;

import java.time.LocalDateTime;

public interface LoginLogDto {
    LocalDateTime getLoginDate();
    String getIpAddress();

}
