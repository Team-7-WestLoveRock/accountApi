package com.nhnacademy.westloverock.accountapi.service;

import com.nhnacademy.westloverock.accountapi.entity.Account;
import com.nhnacademy.westloverock.accountapi.entity.LoginLog;
import com.nhnacademy.westloverock.accountapi.dto.repository.AccountRepository;
import com.nhnacademy.westloverock.accountapi.dto.repository.LoginLogRepository;
import com.nhnacademy.westloverock.accountapi.dto.request.LoginLogRegisterRequest;
import com.nhnacademy.westloverock.accountapi.response.LoginLogDateDto;
import com.nhnacademy.westloverock.accountapi.response.LoginLogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginLogService {
    private final LoginLogRepository loginLogRepository;

    private final AccountRepository accountRepository;

    public LoginLogDto findLoginLogInform(String userId) {
        Long index = accountRepository.findAccountByUserId(userId).orElseThrow().getIdx();
        return loginLogRepository.findLoginLogByAccountIdx(index).orElseThrow();
    }

    public LoginLogDateDto registerLoginLog(String userId, LoginLogRegisterRequest loginLogRegisterRequest) {
        Account account = accountRepository.findAccountByUserId(userId).orElseThrow();

        LocalDateTime localDateTime = LocalDateTime.now();

        LoginLog loginLog = loginLogRepository.findById(account.getIdx()).orElse(LoginLog.builder()
                                                                    .account(account)
                                                                    .ipAddress(loginLogRegisterRequest.getIpAddress())
                                                                    .loginDate(localDateTime)
                                                                    .build());

        loginLogRepository.save(loginLog);

        return LoginLogDateDto.builder()
                .loginDate(localDateTime)
                .build();

    }


}
