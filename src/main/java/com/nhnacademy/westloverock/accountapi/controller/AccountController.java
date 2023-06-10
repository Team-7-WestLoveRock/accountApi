package com.nhnacademy.westloverock.accountapi.controller;

import com.nhnacademy.westloverock.accountapi.dto.request.AccountRegisterRequest;
import com.nhnacademy.westloverock.accountapi.dto.request.AccountStateRequest;
import com.nhnacademy.westloverock.accountapi.response.AccountInformationDto;
import com.nhnacademy.westloverock.accountapi.response.AccountUpdateDto;
import com.nhnacademy.westloverock.accountapi.response.EmailResponseDto;
import com.nhnacademy.westloverock.accountapi.service.AccountService;
import com.nhnacademy.westloverock.accountapi.util.CatchMissingValueUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

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
    public HttpEntity<Map<String, String>> updateAccountState(@PathVariable String userId, @RequestBody @Valid AccountStateRequest accountStateRequest, BindingResult bindingResult) {

        CatchMissingValueUtils.throwMissingValue(bindingResult);
        return new ResponseEntity<>(Map.of("state", accountService.updateAccountState(userId, accountStateRequest).getName()), HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<Map<String, LocalDate>> createAccount(@RequestBody @Valid AccountRegisterRequest accountRegisterRequest, BindingResult bindingResult) {
        CatchMissingValueUtils.throwMissingValue(bindingResult);
        accountService.saveAccount(accountRegisterRequest);
        return new ResponseEntity<>(Map.of("createdAt", LocalDate.now()), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public HttpEntity<AccountUpdateDto> updateAccountInformation(@PathVariable String userId, @RequestBody @Valid AccountRegisterRequest accountRegisterRequest, BindingResult bindingResult) {
        CatchMissingValueUtils.throwMissingValue(bindingResult);
        return new ResponseEntity<>(accountService.updateAccount(userId, accountRegisterRequest), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public HttpEntity<Optional<EmailResponseDto>> findIdByEmail(@PathVariable String email) {
        return new ResponseEntity<>(accountService.findIdByEmail(email), HttpStatus.OK);
    }



}
