package com.nhnacademy.westloverock.accountapi.controller;

import com.nhnacademy.westloverock.accountapi.service.LoginLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loginLog")
@RequiredArgsConstructor
public class LoginLogController {
    private final LoginLogService loginLogService;
}
