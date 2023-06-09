package com.nhnacademy.westloverock.accountapi.repository;

import com.nhnacademy.westloverock.accountapi.domain.State;
import com.nhnacademy.westloverock.accountapi.dto.repository.ProfileRepository;
import com.nhnacademy.westloverock.accountapi.entity.Account;
import com.nhnacademy.westloverock.accountapi.entity.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class ProfileRepositoryTest {
    @Autowired
    ProfileRepository profileRepository;

    @Test
    void save() {
        Account accounts = Account.builder()
                .userId("qaz")
                .password("qdadqwe")
                .name("choo")
                .nickname("한글이요")
                .state(State.ACTIVE)
                .email("asdasd@naver.com")
                .phoneNumber("010-3129-4545")
                .build();

        Profile profile = Profile.builder()
                        .account(accounts)
                        .imagePath("path")
                        .build();

        Profile profile1 = profileRepository.save(profile);
        assertThat(profile1.getAccountIdx()).isEqualTo(accounts.getIdx());
    }
}