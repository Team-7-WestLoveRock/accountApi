package com.nhnacademy.westloverock.accountapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.westloverock.accountapi.dto.request.AccountStateRequest;
import com.nhnacademy.westloverock.accountapi.response.AccountInformationDto;
import com.nhnacademy.westloverock.accountapi.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    @DisplayName("Account 조회")
    void findAccount() throws Exception {
        ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
        AccountInformationDto accountInformationDto = projectionFactory.createProjection(AccountInformationDto.class,
                Map.of("userId", "asd", "password", "qweqweqweqw", "email", "asdad@naver.com"));
        when(accountService.findAccount(any())).thenReturn(accountInformationDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account/api/accounts/westloverock")
                .content(objectMapper.writeValueAsString(accountInformationDto))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("asd"));
    }

    @Test
    @DisplayName("State 갱신")
    void updateAccountState() {
        AccountStateRequest accountStateRequest = AccountStateRequest.builder()
                .state("휴면")
                .build();

//        when(accountService.updateAccount(any(), accountStateRequest).g)
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