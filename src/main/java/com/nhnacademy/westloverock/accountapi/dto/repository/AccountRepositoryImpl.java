package com.nhnacademy.westloverock.accountapi.dto.repository;

import com.nhnacademy.westloverock.accountapi.entity.Account;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class AccountRepositoryImpl extends QuerydslRepositorySupport implements AccountRepositoryCustom {
    public AccountRepositoryImpl() {
        super(Account.class);
    }

//    @Override
//    public Account findAccountById() {
//        QAccount account = QAccount.account;
//        return null;
//    }
}
