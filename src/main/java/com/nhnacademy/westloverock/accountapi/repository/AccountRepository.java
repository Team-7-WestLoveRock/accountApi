package com.nhnacademy.westloverock.accountapi.repository;

import com.nhnacademy.westloverock.accountapi.entity.Account;
import com.nhnacademy.westloverock.accountapi.response.AccountInformationDto;
import com.nhnacademy.westloverock.accountapi.response.AccountStateDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom {
//    Optional<Account> findAccountByUserId(String userId);
    Optional<Account> findAccountByUserId(String userId);
    Optional<AccountInformationDto> findAccountInformationDtoByUserId(String userId);

    Optional<AccountStateDto> findAccountStateDtoByUserId(String userId);
}
