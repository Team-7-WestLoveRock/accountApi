package com.nhnacademy.westloverock.accountapi.service;

import com.nhnacademy.westloverock.accountapi.domain.State;
import com.nhnacademy.westloverock.accountapi.dto.repository.AccountRepository;
import com.nhnacademy.westloverock.accountapi.dto.repository.LoginLogRepository;
import com.nhnacademy.westloverock.accountapi.dto.request.LoginLogRegisterRequest;
import com.nhnacademy.westloverock.accountapi.entity.Account;
import com.nhnacademy.westloverock.accountapi.entity.LoginLog;
import com.nhnacademy.westloverock.accountapi.exception.ObjectNotFound;
import com.nhnacademy.westloverock.accountapi.response.LoginLogDateDto;
import com.nhnacademy.westloverock.accountapi.response.LoginLogDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@ExtendWith(MockitoExtension.class)
class LoginLogServiceTest {

    @Mock
    LoginLogRepository loginLogRepository;

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    LoginLogService loginLogService;

    @Test
    @DisplayName("LoginLog 찾기")
    void findLoginLogInform() {

        LoginLogDto loginLogDto = mock(LoginLogDto.class);

        when(loginLogRepository.findLoginLogByAccount_UserId(any()))
                .thenReturn(Optional.of(loginLogDto));

        when(loginLogDto.getLoginDate())
                .thenReturn(LocalDateTime.now());


        LoginLogDto test = loginLogService.findLoginLogInform("test");


        assertThat(test.getLoginDate())
                .isNotNull()
                .isEqualTo(loginLogDto.getLoginDate());
    }

    @Test
    @DisplayName("LoginLog Error")
    void findLoginLogInformObjectNotFound() {
        when(loginLogRepository.findLoginLogByAccount_UserId(any()))
                .thenReturn(Optional.empty());

        assertThrows(ObjectNotFound.class, () ->
                loginLogService.findLoginLogInform("test"));

    }
    @Test
    @DisplayName("LoginLog 저장")
    void registerLoginLog() {
        when(accountRepository.findAccountByUserId(any()))
                .thenReturn(Optional.of(mock(Account.class)));

        LoginLogRegisterRequest loginLogRegisterRequest = LoginLogRegisterRequest.builder()
                .ipAddress("192.168.1.1")
                .build();

        assertThat(loginLogService.registerLoginLog("test", loginLogRegisterRequest))
                .isNotNull();
    }

    @Test
    @DisplayName("LoginLog 오류")
    void registerLoginLogObjectNotFound() {
        when(accountRepository.findAccountByUserId(any()))
                .thenReturn(Optional.empty());

        LoginLogRegisterRequest loginLogRegisterRequest = mock(LoginLogRegisterRequest.class);

        assertThrows(ObjectNotFound.class, () ->
                loginLogService.registerLoginLog("test", loginLogRegisterRequest));
    }
}