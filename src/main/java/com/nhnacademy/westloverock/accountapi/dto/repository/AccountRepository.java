package com.nhnacademy.westloverock.accountapi.dto.repository;

import com.nhnacademy.westloverock.accountapi.entity.Account;
import com.nhnacademy.westloverock.accountapi.response.AccountInformationDto;
import com.nhnacademy.westloverock.accountapi.response.AccountUpdateDto;
import com.nhnacademy.westloverock.accountapi.response.EmailResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByUserId(String userId);
    Optional<AccountInformationDto> findAccountInformationDtoByUserId(String userId);
    Optional<AccountUpdateDto> findAccountUpdateDtoByUserId(String userId);
    Optional<EmailResponseDto> findEmailResponseDtoByEmail(String email);
}