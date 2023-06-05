package com.nhnacademy.westloverock.accountapi;

import com.nhnacademy.westloverock.accountapi.domain.State;
import com.nhnacademy.westloverock.accountapi.entity.Accounts;
import com.nhnacademy.westloverock.accountapi.entity.Profiles;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ActiveProfiles("dev")
@SpringBootTest
@Transactional
public class ProfilesEntityTest {
    @PersistenceContext
    private EntityManager entityManager;
    @Test
    public void testProfilesEntity() {
        Accounts accounts = Accounts.builder()
                .userId("qaz")
                .password("qdadqwe")
                .name("choo")
                .nickname("asdasdasd")
                .state(State.ACTIVE)
                .email("asdasd@naver.com")
                .phoneNumber("010-3129-4545")
                .build();

        entityManager.persist(accounts);

        Profiles profiles = Profiles.builder()
                .accounts(accounts)
                .imagePath("123qeasdasdas")
                .build();

        entityManager.persist(profiles);

        entityManager.flush();
    }
}
