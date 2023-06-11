package com.nhnacademy.westloverock.accountapi.service;

import com.nhnacademy.westloverock.accountapi.domain.State;
import com.nhnacademy.westloverock.accountapi.dto.repository.AccountRepository;
import com.nhnacademy.westloverock.accountapi.dto.request.AccountRegisterRequest;
import com.nhnacademy.westloverock.accountapi.dto.request.AccountStateRequest;
import com.nhnacademy.westloverock.accountapi.entity.Account;
import com.nhnacademy.westloverock.accountapi.exception.NotMatchState;
import com.nhnacademy.westloverock.accountapi.exception.ObjectNotFound;
import com.nhnacademy.westloverock.accountapi.response.AccountInformationDto;
import com.nhnacademy.westloverock.accountapi.response.AccountUpdateDto;
import com.nhnacademy.westloverock.accountapi.response.EmailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    public final AccountRepository accountRepository;

    public AccountInformationDto findAccount(String userId) {
        return accountRepository.findAccountInformationDtoByUserId(userId)
                .orElseThrow(() -> new ObjectNotFound("아이디에 해당하는 AccountInformationDto 없음"));
    }

    public State updateAccountState(String userId, AccountStateRequest accountStateRequest) {
        if (!State.matches(accountStateRequest.getState())) {
            throw new NotMatchState("없는 상태입니다.");
        }
        Account account = accountRepository.findAccountByUserId(userId).orElseThrow(() -> new ObjectNotFound("아이디에 해당하는 유저 없음"));

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

    public AccountUpdateDto updateAccount(String userId, AccountRegisterRequest accountRegisterRequest) {
        Account account = accountRepository.findAccountByUserId(userId)
                .orElseThrow(() -> new ObjectNotFound("아이디에 해당하는 유저 없음"));

        account.modifyInformation(accountRegisterRequest);

        accountRepository.save(account);

        return accountRepository.findAccountUpdateDtoByUserId(account.getUserId())
                .orElseThrow(() -> new ObjectNotFound("아이디에 해당하는 AccountUpdateDto 없음"));

    }

    public Optional<EmailResponseDto> findIdByEmail(String email) {
        return accountRepository.findEmailResponseDtoByEmail(email);
    }
}
