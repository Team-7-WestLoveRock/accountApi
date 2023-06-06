package com.nhnacademy.westloverock.accountapi.service;

import com.nhnacademy.westloverock.accountapi.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    public final AccountRepository accountRepository;
}
