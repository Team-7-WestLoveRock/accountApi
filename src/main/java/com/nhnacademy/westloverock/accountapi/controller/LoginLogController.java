package com.nhnacademy.westloverock.accountapi.controller;

import com.nhnacademy.westloverock.accountapi.request.LoginLogRegisterRequest;
import com.nhnacademy.westloverock.accountapi.response.LoginLogDateDto;
import com.nhnacademy.westloverock.accountapi.response.LoginLogDto;
import com.nhnacademy.westloverock.accountapi.service.LoginLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/account/api/accounts")
@RequiredArgsConstructor
public class LoginLogController {
    private final LoginLogService loginLogService;

    @GetMapping("/{userId}/loginLogs")
    public HttpEntity<LoginLogDto> findLoginLogInformation(@PathVariable String userId) {
        return new ResponseEntity<>(loginLogService.findLoginLogInform(userId), HttpStatus.OK);
    }
    @PostMapping("/{userId}/loginLog")
    public HttpEntity<LoginLogDateDto> registerLoginLog(@PathVariable String userId, @RequestBody LoginLogRegisterRequest loginLogRegisterRequest) {
        return new ResponseEntity<>(loginLogService.registerLoginLog(userId, loginLogRegisterRequest), HttpStatus.CREATED);
    }
}
