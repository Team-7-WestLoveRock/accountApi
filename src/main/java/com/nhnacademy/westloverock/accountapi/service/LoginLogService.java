package com.nhnacademy.westloverock.accountapi.service;

import com.nhnacademy.westloverock.accountapi.repository.LoginLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginLogService {
    public final LoginLogRepository loginLogRepository;
}
