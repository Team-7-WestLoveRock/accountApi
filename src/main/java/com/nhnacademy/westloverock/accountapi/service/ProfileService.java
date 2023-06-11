package com.nhnacademy.westloverock.accountapi.service;

import com.nhnacademy.westloverock.accountapi.entity.Account;
import com.nhnacademy.westloverock.accountapi.entity.Profile;
import com.nhnacademy.westloverock.accountapi.repository.AccountRepository;
import com.nhnacademy.westloverock.accountapi.repository.ProfileRepository;
import com.nhnacademy.westloverock.accountapi.dto.request.ProfileImagePathRequest;
import com.nhnacademy.westloverock.accountapi.exception.ObjectNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public void updateImagePath(String userId, ProfileImagePathRequest profileImagePathRequest) {
        Account account = accountRepository.findAccountByUserId(userId).orElseThrow(() -> new ObjectNotFound("아이디에 해당하는 유저 없음"));

        Profile profile = Profile.builder()
                .account(account)
                .imagePath(profileImagePathRequest.getImagePath())
                .build();

        profileRepository.save(profile);
    }
}
