package com.nhnacademy.westloverock.accountapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.westloverock.accountapi.dto.request.ProfileImagePathRequest;
import com.nhnacademy.westloverock.accountapi.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfileController.class)
class ProfileControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ProfileService profileService;
    @Test
    void updateAccountState() throws Exception {
        ProfileImagePathRequest profileImagePathRequest = ProfileImagePathRequest.builder()
                .imagePath("/qweqwe/sdfsdf/rwret.txt")
                .build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account/api/accounts/westloverock/profile")
                .content(objectMapper.writeValueAsString(profileImagePathRequest))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk());

    }
}