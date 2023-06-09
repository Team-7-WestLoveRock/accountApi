//package com.nhnacademy.westloverock.accountapi.repository;
//
//import com.nhnacademy.westloverock.accountapi.domain.State;
//import com.nhnacademy.westloverock.accountapi.dto.repository.AccountRepository;
//import com.nhnacademy.westloverock.accountapi.dto.repository.ProfileRepository;
//import com.nhnacademy.westloverock.accountapi.entity.Account;
//import com.nhnacademy.westloverock.accountapi.entity.Profile;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.ActiveProfiles;
//
//import static org.assertj.core.api.Assertions.*;
//
//@DataJpaTest
//@ActiveProfiles("dev")
//class ProfileRepositoryTest {
//    @Autowired
//    TestEntityManager testEntityManager;
//
//    @Autowired
//    ProfileRepository profileRepository;
//
//    @Autowired
//    AccountRepository accountRepository;
//
//    @Test
//    @DisplayName("데이터 삽입")
//    @Order(1)
//    void save() {
//        Account account = Account.builder()
//                .userId("asㅁㄴㅇd")
//                .name("ㅛㅕ감자")
//                .password("qwㅓㅎeqeqeqw")
//                .email("asd@naver.com")
//                .phoneNumber("010-1234-1234")
//                .nickname("ohbhoho")
//                .state(State.ACTIVE)
//                .build();
//        accountRepository.save(account);
////        accountRepository.flush();
//
//        Profile profile = Profile.builder()
//                .account(account)
//                .imagePath("asd/qeqwads/af.jpg")
//                .build();
//
//        testEntityManager.persist(profile);
//
//
//        assertThat(profileRepository.findById(1L).get()).isEqualTo(profile);
//    }
//
//    @Test
//    @DisplayName("업데이트")
//    @Order(2)
//    void update() {
//        Account account = Account.builder()
//                .userId("asd")
//                .name("감자2")
//                .password("qweqeqeqw")
//                .email("asd@naver.com")
//                .phoneNumber("010-1234-1234")
//                .nickname("ohoho")
//                .state(State.ACTIVE)
//                .build();
//
//        Profile profile = Profile.builder()
//                .account(account)
//                .imagePath("asd/qeqwads/af.jpg")
//                .build();
//
//        testEntityManager.persist(profile);
//
//        profile.modifyImagePath("qwe/qwe.png");
//
//        testEntityManager.refresh(profile);
//
//        assertThat(profileRepository.findById(1L).get()).isEqualTo(profile);
//    }
//}