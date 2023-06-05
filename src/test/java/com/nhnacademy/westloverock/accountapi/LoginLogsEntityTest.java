package com.nhnacademy.westloverock.accountapi;

import com.nhnacademy.westloverock.accountapi.domain.State;
import com.nhnacademy.westloverock.accountapi.entity.Accounts;
import com.nhnacademy.westloverock.accountapi.entity.LoginLogs;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("dev")
@SpringBootTest
@Transactional
public class LoginLogsEntityTest {
    @PersistenceContext
    private EntityManager entityManager;
    @Test
    public void testLoginLogsEntity() {
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
        System.out.println("accounts idx: {}" + accounts.getIdx());

        Accounts accounts2 = Accounts.builder()
                .userId("qaz")
                .password("qdadqwe")
                .name("choo")
                .nickname("asdasdasd")
                .state(State.ACTIVE)
                .email("asdasd@naver.com")
                .phoneNumber("010-3129-4545")
                .build();

        entityManager.persist(accounts2);
        System.out.println("accounts2 idx: {}" + accounts2.getIdx());

        LoginLogs loginLogs = LoginLogs.builder()
                .accounts(accounts)
                .loginDate(LocalDateTime.now())
                .ipAddress("19123")
                .build();
        entityManager.persist(loginLogs);

        LoginLogs loginLogs2 = LoginLogs.builder()
                .accounts(accounts2)
                .loginDate(LocalDateTime.now())
                .ipAddress("9999")
                .build();

        entityManager.persist(loginLogs2);

        assertThat(entityManager.find(LoginLogs.class, 1L)).isNotNull();
        assertThat(entityManager.find(LoginLogs.class, 5L)).isNull();

        entityManager.flush();
    }
}
