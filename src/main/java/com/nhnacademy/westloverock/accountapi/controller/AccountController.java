package com.nhnacademy.westloverock.accountapi.controller;

import com.nhnacademy.westloverock.accountapi.domain.State;
import com.nhnacademy.westloverock.accountapi.request.AccountRegisterRequest;
import com.nhnacademy.westloverock.accountapi.request.AccountStateRequest;
import com.nhnacademy.westloverock.accountapi.response.AccountInformationDto;
import com.nhnacademy.westloverock.accountapi.response.AccountStateDto;
import com.nhnacademy.westloverock.accountapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/account/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{userId}")
    public HttpEntity<AccountInformationDto> findAccount(@PathVariable String userId) {
        return new ResponseEntity<>(accountService.findAccount(userId), HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public HttpEntity<Map<String, String>> updateAccountState(@PathVariable String userId, @RequestBody AccountStateRequest accountStateRequest) {
        return new ResponseEntity<>(Map.of("state", accountService.updateAccountState(userId, accountStateRequest).getName()), HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<Map<String, LocalDate>> createAccount(@RequestBody AccountRegisterRequest accountRegisterRequest) {
        accountService.saveAccount(accountRegisterRequest);
        // ToDo userId가 unique이기 때문에 userId가 같다면 예외 발생
        return new ResponseEntity<>(Map.of("createdAt", LocalDate.now()), HttpStatus.CREATED);
    }


}
