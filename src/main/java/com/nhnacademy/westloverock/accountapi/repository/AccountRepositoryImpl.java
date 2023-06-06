package com.nhnacademy.westloverock.accountapi.repository;

import com.nhnacademy.westloverock.accountapi.entity.Account;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class AccountRepositoryImpl extends QuerydslRepositorySupport implements AccountRepositoryCustom {
    public AccountRepositoryImpl() {
        super(Account.class);
    }
}
