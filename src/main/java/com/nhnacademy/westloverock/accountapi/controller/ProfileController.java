package com.nhnacademy.westloverock.accountapi.controller;

import com.nhnacademy.westloverock.accountapi.dto.request.ProfileImagePathRequest;
import com.nhnacademy.westloverock.accountapi.service.ProfileService;
import com.nhnacademy.westloverock.accountapi.util.CatchMissingValueUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account/api/accounts")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/{userId}/profile")
    public HttpEntity<Void> updateAccountState(@PathVariable String userId, @RequestBody @Valid ProfileImagePathRequest profileImagePathRequest, BindingResult bindingResult) {
        CatchMissingValueUtils.throwMissingValue(bindingResult);
        profileService.updateImagePath(userId, profileImagePathRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
// 이메일 던져서 id 만석
