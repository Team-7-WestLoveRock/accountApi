package com.nhnacademy.westloverock.accountapi.repository;

import com.nhnacademy.westloverock.accountapi.entity.Profile;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ProfileRepositoryImpl extends QuerydslRepositorySupport implements ProfileRepositoryCustom {
    public ProfileRepositoryImpl() {
        super(Profile.class);
    }
}
