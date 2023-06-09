package com.nhnacademy.westloverock.accountapi.service;

import com.nhnacademy.westloverock.accountapi.entity.Account;
import com.nhnacademy.westloverock.accountapi.entity.Profile;
import com.nhnacademy.westloverock.accountapi.dto.repository.AccountRepository;
import com.nhnacademy.westloverock.accountapi.dto.repository.ProfileRepository;
import com.nhnacademy.westloverock.accountapi.dto.request.ProfileImagePathRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final AccountRepository accountRepository;

    public void updateImagePath(String userId, ProfileImagePathRequest profileImagePathRequest) {
        Account account = accountRepository.findAccountByUserId(userId).orElseThrow();

        Profile profile = Profile.builder()
                .account(account)
                .imagePath(profileImagePathRequest.getImagePath())
                .build();
//        Profile profile = new Profile(account.getIdx(), profileImagePathRequest.getImagePath(), account);

        profileRepository.save(profile);
        // ToDo email로 user_id만 가져온다
    }
}
