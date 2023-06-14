package com.nhnacademy.westloverock.accountapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.westloverock.accountapi.domain.State;
import com.nhnacademy.westloverock.accountapi.dto.request.AccountRegisterRequest;
import com.nhnacademy.westloverock.accountapi.dto.request.AccountStateRequest;
import com.nhnacademy.westloverock.accountapi.dto.response.AccountInformationDto;
import com.nhnacademy.westloverock.accountapi.dto.response.AccountUpdateDto;
import com.nhnacademy.westloverock.accountapi.dto.response.EmailResponseDto;
import com.nhnacademy.westloverock.accountapi.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        when(accountService.findAccount("asd")).thenReturn(accountInformationDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account/api/accounts/asd")
                .accept(MediaType.APPLICATION_JSON);


        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.userId").value("asd"));

    }

    @Test
    @DisplayName("State 갱신")
    void updateAccountState() throws Exception {
        AccountStateRequest accountStateRequest = AccountStateRequest.builder()
                .state("휴면")
                .build();

        when(accountService.updateAccountState(any(), any()))
                .thenReturn(State.getState(accountStateRequest.getState()));

        Map<String, String> map = new HashMap<>();
        map.put("state", accountStateRequest.getState());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account/api/accounts/westloverock")
                        .content(objectMapper.writeValueAsString(accountStateRequest))
                        .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value(map.get("state")));
    }

    @Test
    void createAccount() throws Exception {
        AccountRegisterRequest accountRegisterRequest = AccountRegisterRequest.builder()
                .userId("qwe")
                .password("qeqeqw123")
                .name("바마나")
                .nickname("adqrsdvsfd")
                .phoneNumber("010-1234-1234")
                .email("qwe@naver.com")
                .build();


        Map<String, LocalDate> map = new HashMap<>();
        map.put("createdAt", LocalDate.now());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account/api/accounts")
                .content(objectMapper.writeValueAsString(accountRegisterRequest))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.createdAt").value(map.get("createdAt").toString()));
    }
    @Test
    void updateAccountInformation() throws Exception {
        AccountRegisterRequest accountRegisterRequest = AccountRegisterRequest.builder()
                .userId("qwe")
                .password("qweqwasdasd")
                .name("라면땅")
                .nickname("ramen")
                .phoneNumber("010-1234-1234")
                .email("qwe@naver.com")
                .build();

        AccountUpdateDto accountUpdateDto = AccountUpdateDto.builder()
                .userId("qwe")
                .password("qweqwasdasd")
                .name("라면땅")
                .nickname("ramen")
                .phoneNumber("010-1234-1234")
                .email("qwe@naver.com")
                .build();

        when(accountService.updateAccount(any(), any())).thenReturn(accountUpdateDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/account/api/accounts/westloverock")
                .content(objectMapper.writeValueAsString(accountRegisterRequest))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(accountUpdateDto.getUserId()))
                .andExpect(jsonPath("$.password").value(accountUpdateDto.getPassword()))
                .andExpect(jsonPath("$.name").value(accountUpdateDto.getName()))
                .andExpect(jsonPath("$.nickname").value(accountUpdateDto.getNickname()))
                .andExpect(jsonPath("$.phoneNumber").value(accountUpdateDto.getPhoneNumber()))
                .andExpect(jsonPath("$.email").value(accountUpdateDto.getEmail()));

    }

    @Test
    void findIdByEmail() throws Exception {
        ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
        EmailResponseDto emailResponseDto = projectionFactory.createProjection(EmailResponseDto.class,
                Map.of("userId", "qwe"));
        when(accountService.findIdByEmail("asd@naver.com")).thenReturn(Optional.of(emailResponseDto));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account/api/accounts/email/asd@naver.com")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(emailResponseDto.getUserId()));
    }

    @Test
    @DisplayName("유저 유무 확인 - 정상")
    void existByUserId() throws Exception {
        when(accountService.existByUserId("westloverock")).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account/api/accounts/westloverock/exist");

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}