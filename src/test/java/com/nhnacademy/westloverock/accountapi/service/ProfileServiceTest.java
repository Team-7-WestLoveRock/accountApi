package com.nhnacademy.westloverock.accountapi.service;

import com.nhnacademy.westloverock.accountapi.dto.repository.AccountRepository;
import com.nhnacademy.westloverock.accountapi.dto.repository.ProfileRepository;
import com.nhnacademy.westloverock.accountapi.dto.request.ProfileImagePathRequest;
import com.nhnacademy.westloverock.accountapi.entity.Account;
import com.nhnacademy.westloverock.accountapi.entity.Profile;
import com.nhnacademy.westloverock.accountapi.exception.ObjectNotFound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {
    @Mock
    ProfileRepository profileRepository;
    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    ProfileService profileService;

    @Test
    @DisplayName("경로 업데이트 성공")
    void updateImagePath() {
        when(accountRepository.findAccountByUserId(any()))
                .thenReturn(Optional.of(mock(Account.class)));

        ProfileImagePathRequest profileImagePathRequest = mock(ProfileImagePathRequest.class);

        profileService.updateImagePath(any(), profileImagePathRequest);
        verify(profileRepository).save(any(Profile.class));
    }

    @Test
    @DisplayName("경로 업데이트 실패")
    void updateImagePathObjectNotFound() {
        when(accountRepository.findAccountByUserId(any()))
                .thenReturn(Optional.empty());
        ProfileImagePathRequest profileImagePathRequest = mock(ProfileImagePathRequest.class);
        assertThrows(ObjectNotFound.class, () ->
                profileService.updateImagePath("test", profileImagePathRequest));
    }
}