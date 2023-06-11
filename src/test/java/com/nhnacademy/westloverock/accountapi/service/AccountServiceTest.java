package com.nhnacademy.westloverock.accountapi.service;

import com.nhnacademy.westloverock.accountapi.domain.State;
import com.nhnacademy.westloverock.accountapi.dto.repository.AccountRepository;
import com.nhnacademy.westloverock.accountapi.dto.request.AccountRegisterRequest;
import com.nhnacademy.westloverock.accountapi.dto.request.AccountStateRequest;
import com.nhnacademy.westloverock.accountapi.entity.Account;
import com.nhnacademy.westloverock.accountapi.exception.ObjectNotFound;
import com.nhnacademy.westloverock.accountapi.response.AccountInformationDto;
import com.nhnacademy.westloverock.accountapi.response.AccountUpdateDto;
import com.nhnacademy.westloverock.accountapi.response.EmailResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountService accountService;

    @Test
    @DisplayName("조회 - 정상")
    void findAccount() {
        ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
        AccountInformationDto accountInformationDto = projectionFactory.createProjection(AccountInformationDto.class,
                Map.of("userId", "qwe", "password", "asd", "email", "zxc@naver.com"));

        when(accountRepository.findAccountInformationDtoByUserId(any())).thenReturn(Optional.of(accountInformationDto));

        AccountRegisterRequest accountRegisterRequest = AccountRegisterRequest.builder()
                .userId("qwe")
                .password("1234")
                .name("만두")
                .nickname("감차먼두")
                .email("asd@naver.com")
                .phoneNumber("010-1234-1234")
                .build();

        assertThat(accountService.findAccount(accountRegisterRequest.getUserId()).getUserId()).isEqualTo(accountInformationDto.getUserId());
        assertThat(accountService.findAccount(accountRegisterRequest.getUserId()).getPassword()).isEqualTo(accountInformationDto.getPassword());
        assertThat(accountService.findAccount(accountRegisterRequest.getUserId()).getEmail()).isEqualTo(accountInformationDto.getEmail());
    }
    @Test
    @DisplayName("조회 - 비정상")
    void findAccountError() {


        when(accountRepository.findAccountInformationDtoByUserId(any())).thenThrow(ObjectNotFound.class);

        AccountRegisterRequest accountRegisterRequest = AccountRegisterRequest.builder()
                .userId("qwe")
                .password("1234")
                .name("만두")
                .nickname("감차먼두")
                .email("asd@naver.com")
                .phoneNumber("010-1234-1234")
                .build();

        String userId = accountRegisterRequest.getUserId();
        assertThrows(ObjectNotFound.class, () -> accountService.findAccount(userId));

    }

    @Test
    @DisplayName("상태 변경 - 정상")
    void updateAccountState() {
        Account account = Account.builder()
                .userId("만석짱짱")
                .password("qwaskjc")
                .name("믹느")
                .phoneNumber("010-9415-3892")
                .email("qweas@naver.com")
                .state(State.ACTIVE)
                .nickname("qweasas")
                .build();

        when(accountRepository.findAccountByUserId(any())).thenReturn(Optional.of(account));
        AccountStateRequest accountStateRequest = AccountStateRequest.builder()
                .state("휴면")
                .build();

        assertThat(accountService.updateAccountState(account.getUserId(), accountStateRequest)).isEqualTo(State.getState(accountStateRequest.getState()));
    }

    @Test
    @DisplayName("회원 저장")
    void saveAccount() {
        AccountRegisterRequest accountRegisterRequest = AccountRegisterRequest.builder()
                .userId("qwe")
                .password("1234")
                .name("만두")
                .nickname("감차먼두")
                .email("asd@naver.com")
                .phoneNumber("010-1234-1234")
                .build();

        accountService.saveAccount(accountRegisterRequest);
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    @DisplayName("회원 갱신")
    void updateAccount() {
        Account account = Account.builder()
                .userId("만석짱짱")
                .password("qwaskjc")
                .name("믹느")
                .phoneNumber("010-9415-3892")
                .email("qweas@naver.com")
                .state(State.ACTIVE)
                .nickname("qweasas")
                .build();
        when(accountRepository.findAccountByUserId(any())).thenReturn(Optional.of(account));

        AccountRegisterRequest accountRegisterRequest = AccountRegisterRequest.builder()
                .userId("qwe")
                .password("1234")
                .name("만두")
                .nickname("감차먼두")
                .email("asd@naver.com")
                .phoneNumber("010-1234-1234")
                .build();

        AccountUpdateDto accountUpdateDto = AccountUpdateDto.builder()
                .userId("qwe")
                .password("1234")
                .name("만두")
                .nickname("감차먼두")
                .email("asd@naver.com")
                .phoneNumber("010-1234-1234")
                .build();

        when(accountRepository.findAccountUpdateDtoByUserId(accountRegisterRequest.getUserId())).thenReturn(Optional.of(accountUpdateDto));

        assertThat(accountService.updateAccount(account.getUserId(), accountRegisterRequest).getUserId()).isEqualTo(accountUpdateDto.getUserId());
        assertThat(accountService.updateAccount(account.getUserId(), accountRegisterRequest).getPassword()).isEqualTo(accountUpdateDto.getPassword());
        assertThat(accountService.updateAccount(account.getUserId(), accountRegisterRequest).getName()).isEqualTo(accountUpdateDto.getName());
        assertThat(accountService.updateAccount(account.getUserId(), accountRegisterRequest).getNickname()).isEqualTo(accountUpdateDto.getNickname());
        assertThat(accountService.updateAccount(account.getUserId(), accountRegisterRequest).getPhoneNumber()).isEqualTo(accountUpdateDto.getPhoneNumber());
        assertThat(accountService.updateAccount(account.getUserId(), accountRegisterRequest).getEmail()).isEqualTo(accountUpdateDto.getEmail());
    }

    @Test
    @DisplayName("이메일로 아이디 찾기")
    void findIdByEmail() {
        ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();

        Account account = Account.builder()
                .userId("만석짱짱")
                .password("qwaskjc")
                .name("믹느")
                .phoneNumber("010-9415-3892")
                .email("qweas@naver.com")
                .state(State.ACTIVE)
                .nickname("qweasas")
                .build();

        EmailResponseDto emailResponseDto = projectionFactory.createProjection(EmailResponseDto.class,
                Map.of("userId", "만석짱짱"));

        when(accountRepository.findEmailResponseDtoByEmail(any())).thenReturn(Optional.of(emailResponseDto));

        assertThat(accountService.findIdByEmail(account.getEmail()).get().getUserId()).isEqualTo(emailResponseDto.getUserId());
    }
}