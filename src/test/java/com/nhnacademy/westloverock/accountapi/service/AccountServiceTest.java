package com.nhnacademy.westloverock.accountapi.service;

import com.nhnacademy.westloverock.accountapi.domain.State;
import com.nhnacademy.westloverock.accountapi.dto.repository.AccountRepository;
import com.nhnacademy.westloverock.accountapi.dto.request.AccountRegisterRequest;
import com.nhnacademy.westloverock.accountapi.dto.request.AccountStateRequest;
import com.nhnacademy.westloverock.accountapi.entity.Account;
import com.nhnacademy.westloverock.accountapi.exception.ObjectNotFound;
import com.nhnacademy.westloverock.accountapi.response.AccountInformationDto;
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
import static org.mockito.Mockito.when;

@ActiveProfiles("dev")
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

//    @Test
//    @DisplayName("상태 변경 - 정상")
//    void updateAccountState() {
//        Account account = Account.builder()
//                .userId("만석짱짱")
//                .password("qwaskjc")
//                .name("믹느")
//                .phoneNumber("010-9415-3892")
//                .email("qweas@naver.com")
//                .state(State.ACTIVE)
//                .nickname("qweasas")
//                .build();
//
//        when(accountRepository.findAccountByUserId(any())).thenReturn(Optional.of(account));
//        AccountStateRequest accountStateRequest = AccountStateRequest.builder()
//                .state("휴면")
//                .build();

//        account.modifyStatus(accountStateRequest);
//
//        accountRepository.save(account);

//        assertThat(accountService.updateAccountState(account.getUserId(), accountStateRequest)).isEqualTo(accountStateRequest.getState().);
//    }

    @Test
    void saveAccount() {
    }

    @Test
    void updateAccount() {
    }

    @Test
    void findIdByEmail() {
    }
}