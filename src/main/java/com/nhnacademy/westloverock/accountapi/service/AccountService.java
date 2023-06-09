package com.nhnacademy.westloverock.accountapi.service;

import com.nhnacademy.westloverock.accountapi.domain.State;
import com.nhnacademy.westloverock.accountapi.entity.Account;
import com.nhnacademy.westloverock.accountapi.repository.AccountRepository;
import com.nhnacademy.westloverock.accountapi.request.AccountRegisterRequest;
import com.nhnacademy.westloverock.accountapi.request.AccountStateRequest;
import com.nhnacademy.westloverock.accountapi.request.AccountUpdateRequest;
import com.nhnacademy.westloverock.accountapi.response.AccountInformationDto;
import com.nhnacademy.westloverock.accountapi.response.AccountUpdateDto;
import com.nhnacademy.westloverock.accountapi.response.EmailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    public final AccountRepository accountRepository;

    public AccountInformationDto findAccount(String userId) {
        Optional<AccountInformationDto> accountInformationDto = accountRepository.findAccountInformationDtoByUserId(userId);
        if (Objects.isNull(accountInformationDto)) {
            // ToDo customError 작성
            throw new NoSuchElementException("아이디에 해당하는 유저 없음");
        }
        return accountInformationDto.get();
    }

    public State updateAccountState(String userId, AccountStateRequest accountStateRequest) {
        // ToDo 올바른 enum값이 들어왔는지 검사하는 코드 필요
//        if (!State.matches(accountStateRequest.getState())) {
//            throw new RuntimeException();
//        }
        Optional<Account> objectAccount = accountRepository.findAccountByUserId(userId);
        if (Objects.isNull(objectAccount)) {
            // ToDo customError 작성
            throw new NoSuchElementException("아이디에 해당하는 유저 없음");
        }

        Account account = objectAccount.get();
        account.modifyStatus(accountStateRequest);
        accountRepository.save(account);
        return account.getState();
    }
    public void saveAccount(AccountRegisterRequest accountRegisterRequest) {
        Account account = Account.builder()
                .userId(accountRegisterRequest.getUserId())
                .password(accountRegisterRequest.getPassword())
                .name(accountRegisterRequest.getName())
                .nickname(accountRegisterRequest.getNickname())
                .state(State.ACTIVE)
                .email(accountRegisterRequest.getEmail())
                .phoneNumber(accountRegisterRequest.getPhoneNumber())
                .build();

        accountRepository.save(account);
    }

    public AccountUpdateDto updateAccount(String userId, AccountUpdateRequest accountUpdateRequest) {
        Optional<Account> optionalAccount = accountRepository.findAccountByUserId(userId);
        if (Objects.isNull(optionalAccount)) {
            // ToDo customError 작성
            throw new NoSuchElementException("아이디에 해당하는 유저 없음");
        }
        Account account = optionalAccount.get();
        account.modifyInformation(accountUpdateRequest);
        accountRepository.save(account);

        Optional<AccountUpdateDto> optionalAccountUpdateDto = accountRepository.findAccountUpdateDtoByUserId(userId);
        if (Objects.isNull(optionalAccountUpdateDto)) {
            // ToDo customError 작성
            throw new NoSuchElementException("아이디에 해당하는 유저 없음");
        }
        return optionalAccountUpdateDto.get();
    }

    public EmailResponseDto findIdByEmail(String email) {
        return accountRepository.findEmailResponseDtoByEmail(email).orElseThrow();
    }
}
