//package com.nhnacademy.westloverock.accountapi;
//
//import com.nhnacademy.westloverock.accountapi.domain.State;
//import com.nhnacademy.westloverock.accountapi.entity.Account;
//import com.nhnacademy.westloverock.accountapi.entity.LoginLog;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import java.time.LocalDateTime;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ActiveProfiles("dev")
//@SpringBootTest
//@Transactional
//public class LoginLogEntityTest {
//    @PersistenceContext
//    private EntityManager entityManager;
//    @Test
//    public void testLoginLogsEntity() {
//        Account accounts = Account.builder()
//                .userId("qaz")
//                .password("qdadqwe")
//                .name("choo")
//                .nickname("한글이요")
//                .state(State.ACTIVE)
//                .email("asdasd@naver.com")
//                .phoneNumber("010-3129-4545")
//                .build();
//
//        entityManager.persist(accounts);
//        System.out.println("accounts idx: {}" + accounts.getIdx());
//
//        Account accounts2 = Account.builder()
//                .userId("qaz")
//                .password("qdadqwe")
//                .name("choo")
//                .nickname("asdasdasd")
//                .state(State.ACTIVE)
//                .email("asdasd@naver.com")
//                .phoneNumber("010-3129-4545")
//                .build();
//
//        entityManager.persist(accounts2);
//        System.out.println("accounts2 idx: {}" + accounts2.getIdx());
//
//        LoginLog loginLog = LoginLog.builder()
//                .account(accounts)
//                .loginDate(LocalDateTime.now())
//                .ipAddress("19123")
//                .build();
//        entityManager.persist(loginLog);
//
//        LoginLog loginLog2 = LoginLog.builder()
//                .account(accounts2)
//                .loginDate(LocalDateTime.now())
//                .ipAddress("9999")
//                .build();
//
//        entityManager.persist(loginLog2);
//
//        assertThat(entityManager.find(LoginLog.class, 1L)).isNotNull();
//        assertThat(entityManager.find(LoginLog.class, 5L)).isNull();
//
//        entityManager.flush();
//    }
//}
