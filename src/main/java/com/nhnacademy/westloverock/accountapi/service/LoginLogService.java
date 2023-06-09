package com.nhnacademy.westloverock.accountapi.service;

import com.nhnacademy.westloverock.accountapi.entity.Account;
import com.nhnacademy.westloverock.accountapi.entity.LoginLog;
import com.nhnacademy.westloverock.accountapi.repository.AccountRepository;
import com.nhnacademy.westloverock.accountapi.repository.LoginLogRepository;
import com.nhnacademy.westloverock.accountapi.dto.request.LoginLogRegisterRequest;
import com.nhnacademy.westloverock.accountapi.exception.ObjectNotFound;
import com.nhnacademy.westloverock.accountapi.dto.response.LoginLogDateDto;
import com.nhnacademy.westloverock.accountapi.dto.response.LoginLogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginLogService {
    private final LoginLogRepository loginLogRepository;

    private final AccountRepository accountRepository;

    public LoginLogDto findLoginLogInform(String userId) {
        return loginLogRepository.findLoginLogByAccount_UserId(userId)
                .orElseThrow(() -> new ObjectNotFound("아이디에 해당하는 loginLog 없음"));
    }

    @Transactional
    public LoginLogDateDto registerLoginLog(String userId, LoginLogRegisterRequest loginLogRegisterRequest) {
        Account account = accountRepository.findAccountByUserId(userId).orElseThrow(() -> new ObjectNotFound("아이디에 해당하는 유저 없음"));

        LoginLog loginLog = LoginLog.builder()
                .account(account)
                .ipAddress(loginLogRegisterRequest.getIpAddress())
                .loginDate(LocalDateTime.now())
                .build();

        loginLogRepository.save(loginLog);

        return LoginLogDateDto.builder()
                .loginDate(loginLog.getLoginDate())
                .build();
    }


}
