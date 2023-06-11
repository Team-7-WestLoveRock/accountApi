package com.nhnacademy.westloverock.accountapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.westloverock.accountapi.entity.Account;
import com.nhnacademy.westloverock.accountapi.response.AccountInformationDto;
import com.nhnacademy.westloverock.accountapi.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    AccountService accountService;

    @Test
    void findAccount() {
//        ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
//        AccountInformationDto accountInformationDto = projectionFactory.createProjection(AccountInformationDto.class,
//                Map.of("userId", "asd", "password", "qweqweqweqw", "email", "asdad@naver.com"));
        when(accountService.findAccount(any())).thenReturn(mock(AccountInformationDto.class));

//        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account/api/accounts/westloverock")
//                .content(objectMapper.writeValueAsString())
    }

    @Test
    void updateAccountState() {

    }

    @Test
    void createAccount() {

    }

    @Test
    void updateAccountInformation() {

    }

    @Test
    void findIdByEmail() {

    }
}