package com.nhnacademy.westloverock.accountapi.controller;

import com.nhnacademy.westloverock.accountapi.request.AccountStateRequest;
import com.nhnacademy.westloverock.accountapi.request.ProfileImagePathRequest;
import com.nhnacademy.westloverock.accountapi.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account/api/accounts")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/{userId}/profile")
    public HttpEntity<Void> updateAccountState(@PathVariable String userId, @RequestBody ProfileImagePathRequest profileImagePathRequest) {
        profileService.updateImagePath(userId, profileImagePathRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
// 이메일 던져서 id 만석
