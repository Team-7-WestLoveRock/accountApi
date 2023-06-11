package com.nhnacademy.westloverock.accountapi.dto.repository;

import com.nhnacademy.westloverock.accountapi.domain.State;
import com.nhnacademy.westloverock.accountapi.entity.Account;
import com.nhnacademy.westloverock.accountapi.entity.LoginLog;
import com.nhnacademy.westloverock.accountapi.response.LoginLogDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoginLogRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    LoginLogRepository loginLogRepository;

    @Autowired
    AccountRepository accountRepository;

    @Test
    @DisplayName("데이터 삽입")
    @Order(1)
    void save() {
        Account account = Account.builder()
                .userId("qeasd")
                .password("wresfsdf")
                .name("유수지")
                .email("qafa@naver.com")
                .phoneNumber("010-1234-1234")
                .nickname("ohbhoho")
                .state(State.ACTIVE)
                .build();
        testEntityManager.persist(account);

        LoginLog loginLog = LoginLog.builder()
                .account(account)
                .loginDate(LocalDateTime.now())
                .ipAddress("192.168.1.34")
                .build();

        loginLogRepository.save(loginLog);
        assertThat(testEntityManager.getId(loginLog)).isEqualTo(loginLog.getAccountIdx());


    }

    @Test
    @DisplayName("업데이트")
    @Order(2)
    void update() {
        Account account = Account.builder()
                .userId("qeasd")
                .password("wresfsdf")
                .name("유수지")
                .email("qafa@naver.com")
                .phoneNumber("010-1234-1234")
                .nickname("ohbhoho")
                .state(State.ACTIVE)
                .build();

        testEntityManager.persist(account);

        LoginLog loginLog = LoginLog.builder()
                .account(account)
                .loginDate(LocalDateTime.now())
                .ipAddress("192.168.1.1")
                .build();

        testEntityManager.persist(loginLog);


        ReflectionTestUtils.setField(loginLog, "loginDate", LocalDateTime.now().plusDays(1));

        assertThat(loginLogRepository.findById(loginLog.getAccountIdx()).get().getAccountIdx()).isEqualTo(loginLog.getAccountIdx());
        assertThat(loginLogRepository.findById(loginLog.getAccountIdx()).get().getLoginDate()).isEqualTo(loginLog.getLoginDate());
        assertThat(loginLogRepository.findById(loginLog.getAccountIdx()).get().getIpAddress()).isEqualTo(loginLog.getIpAddress());
    }

    @Test
    @DisplayName("조회")
    @Order(3)
    void find() {
        Account account = Account.builder()
                .userId("qeasd")
                .password("wresfsdf")
                .name("유수지")
                .email("qafa@naver.com")
                .phoneNumber("010-1234-1234")
                .nickname("ohbhoho")
                .state(State.ACTIVE)
                .build();
        testEntityManager.persist(account);

        LoginLog loginLog = LoginLog.builder()
                .account(account)
                .loginDate(LocalDateTime.now())
                .ipAddress("192.168.1.34")
                .build();

        testEntityManager.persist(loginLog);
        assertThat(loginLogRepository.findById(loginLog.getAccountIdx()).get().getAccountIdx()).isEqualTo(loginLog.getAccountIdx());
        assertThat(loginLogRepository.findById(loginLog.getAccountIdx()).get().getLoginDate()).isEqualTo(loginLog.getLoginDate());
        assertThat(loginLogRepository.findById(loginLog.getAccountIdx()).get().getIpAddress()).isEqualTo(loginLog.getIpAddress());

    }

    @Test
    @DisplayName("삭제")
    @Order(4)
    void delete() {
        Account account = Account.builder()
                .userId("qeasd")
                .password("wresfsdf")
                .name("유수지")
                .email("qafa@naver.com")
                .phoneNumber("010-1234-1234")
                .nickname("ohbhoho")
                .state(State.ACTIVE)
                .build();
        testEntityManager.persist(account);

        LoginLog loginLog = LoginLog.builder()
                .account(account)
                .loginDate(LocalDateTime.now())
                .ipAddress("192.168.1.34")
                .build();

        loginLogRepository.save(loginLog);
        loginLogRepository.delete(loginLog);

        assertThat(loginLogRepository.findById(loginLog.getAccountIdx())).isEmpty();
    }

    @Test
    @DisplayName("account idx로 loginLogDto 호출")
    void findLoginLogByAccountIdx() {
        Account account = Account.builder()
                .userId("qeasd")
                .password("wresfsdf")
                .name("유수지")
                .email("qafa@naver.com")
                .phoneNumber("010-1234-1234")
                .nickname("ohbhoho")
                .state(State.ACTIVE)
                .build();
        testEntityManager.persist(account);

        LoginLog loginLog = LoginLog.builder()
                .account(account)
                .loginDate(LocalDateTime.now())
                .ipAddress("192.168.1.34")
                .build();

        loginLogRepository.save(loginLog);

        LoginLogDto loginLogDto = loginLogRepository.findLoginLogByAccount_UserId(account.getUserId()).get();

        assertThat(loginLogDto.getLoginDate()).isEqualTo(loginLog.getLoginDate());
        assertThat(loginLogDto.getIpAddress()).isEqualTo(loginLog.getIpAddress());

    }
}