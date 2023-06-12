package com.nhnacademy.westloverock.accountapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.westloverock.accountapi.dto.request.LoginLogRegisterRequest;
import com.nhnacademy.westloverock.accountapi.dto.response.LoginLogDateDto;
import com.nhnacademy.westloverock.accountapi.dto.response.LoginLogDto;
import com.nhnacademy.westloverock.accountapi.service.LoginLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginLogController.class)
class LoginLogControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    LoginLogService loginLogService;
    @Test
    void findLoginLogInformation() throws Exception {
        ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
        LoginLogDto loginLogDto = projectionFactory.createProjection(LoginLogDto.class,
                Map.of("loginDate", LocalDateTime.now(), "ipAddress", "192.168.1.1"));

        when(loginLogService.findLoginLogInform(any())).thenReturn(loginLogDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account/api/accounts/westloverock/loginLogs")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(jsonPath("$.loginDate").isNotEmpty())
                .andExpect(jsonPath("$.ipAddress").value(loginLogDto.getIpAddress()));



    }

    @Test
    void registerLoginLog() throws Exception {
        LoginLogDateDto loginLogDateDto = LoginLogDateDto.builder()
                .loginDate(LocalDateTime.now())
                .build();

        LoginLogRegisterRequest loginLogRegisterRequest = LoginLogRegisterRequest.builder()
                .ipAddress("192.168.1.1")
                .build();

        when(loginLogService.registerLoginLog(any(), any())).thenReturn(loginLogDateDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account/api/accounts/westloverock/loginLog")
                .content(objectMapper.writeValueAsString(loginLogRegisterRequest))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.loginDate").isNotEmpty());

    }
}