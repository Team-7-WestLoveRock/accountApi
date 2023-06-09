package com.nhnacademy.westloverock.accountapi.dto.repository;

import com.nhnacademy.westloverock.accountapi.entity.LoginLog;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class LoginLogRepositoryImpl extends QuerydslRepositorySupport implements LoginLogRepositoryCustom {
    public LoginLogRepositoryImpl() {
        super(LoginLog.class);
    }
}
