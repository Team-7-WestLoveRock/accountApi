//package com.nhnacademy.westloverock.accountapi;
//
//import com.nhnacademy.westloverock.accountapi.domain.State;
//import com.nhnacademy.westloverock.accountapi.entity.Account;
//import com.nhnacademy.westloverock.accountapi.entity.Profile;
//import com.nhnacademy.westloverock.accountapi.dto.repository.ProfileRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//@ActiveProfiles("dev")
//@SpringBootTest
//@Transactional
//public class ProfilesEntityTest {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Autowired
//    private ProfileRepository profileRepository;
//    @Test
//    public void testProfilesEntity() {
//        Account accounts = Account.builder()
//                .userId("qaz")
//                .password("qdadqwe")
//                .name("choo")
//                .nickname("asdasdasd")
//                .state(State.ACTIVE)
//                .email("asdasd@naver.com")
//                .phoneNumber("010-3129-4545")
//                .build();
//
//        entityManager.persist(accounts);
//
//        Profile profiles = Profile.builder()
//                .account(accounts)
//                .imagePath("123qeasdasdas")
//                .build();
//
//        entityManager.persist(profiles);
//
//        entityManager.flush();
//    }
//
//    @Test
//    void findById(){
//        profileRepository.findAll().forEach(System.out::println);
//    }
//}