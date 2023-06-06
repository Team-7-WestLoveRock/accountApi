package com.nhnacademy.westloverock.accountapi.service;

import com.nhnacademy.westloverock.accountapi.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
}
