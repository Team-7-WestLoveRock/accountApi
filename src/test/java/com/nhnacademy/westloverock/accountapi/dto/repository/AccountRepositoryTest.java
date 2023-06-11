package com.nhnacademy.westloverock.accountapi.dto.repository;

import com.nhnacademy.westloverock.accountapi.domain.State;
import com.nhnacademy.westloverock.accountapi.dto.repository.AccountRepository;
import com.nhnacademy.westloverock.accountapi.dto.request.AccountStateRequest;
import com.nhnacademy.westloverock.accountapi.entity.Account;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    AccountRepository accountRepository;

    @Test
    @DisplayName("데이터 삽입")
    @Order(1)
    void save() {
        Account account = Account.builder()
                .userId("만석짱짱")
                .password("qwaskjc")
                .name("믹느")
                .phoneNumber("010-9415-3892")
                .email("qweas@naver.com")
                .state(State.ACTIVE)
                .nickname("qweasas")
                .build();

        accountRepository.save(account);

        assertThat(testEntityManager.find(Account.class, account.getIdx()).getIdx()).isEqualTo(account.getIdx());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getUserId()).isEqualTo(account.getUserId());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getPassword()).isEqualTo(account.getPassword());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getName()).isEqualTo(account.getName());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getPhoneNumber()).isEqualTo(account.getPhoneNumber());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getEmail()).isEqualTo(account.getEmail());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getState()).isEqualTo(account.getState());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getNickname()).isEqualTo(account.getNickname());


    }
    @Test
    @DisplayName("업데이트")
    @Order(2)
    void update() {
        Account account = Account.builder()
                .userId("만석짱짱")
                .password("qwaskjc")
                .name("믹느")
                .phoneNumber("010-9415-3892")
                .email("qweas@naver.com")
                .state(State.ACTIVE)
                .nickname("qweasas")
                .build();

        testEntityManager.persist(account);

        AccountStateRequest accountStateRequest = AccountStateRequest.builder()
                .state("휴면")
                .build();

        account.modifyStatus(accountStateRequest);
        testEntityManager.flush();
        testEntityManager.clear();

        assertThat(testEntityManager.find(Account.class, account.getIdx()).getIdx()).isEqualTo(account.getIdx());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getUserId()).isEqualTo(account.getUserId());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getPassword()).isEqualTo(account.getPassword());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getName()).isEqualTo(account.getName());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getPhoneNumber()).isEqualTo(account.getPhoneNumber());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getEmail()).isEqualTo(account.getEmail());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getState()).isEqualTo(account.getState());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getNickname()).isEqualTo(account.getNickname());
    }

    @Test
    @DisplayName("조회")
    @Order(3)
    void find() {
        Account account = Account.builder()
                .userId("만석짱짱")
                .password("qwaskjc")
                .name("믹느")
                .phoneNumber("010-9415-3892")
                .email("qweas@naver.com")
                .state(State.ACTIVE)
                .nickname("qweasas")
                .build();

        testEntityManager.persist(account);
        testEntityManager.flush();
        testEntityManager.clear();

        assertThat(testEntityManager.find(Account.class, account.getIdx()).getIdx()).isEqualTo(account.getIdx());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getUserId()).isEqualTo(account.getUserId());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getPassword()).isEqualTo(account.getPassword());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getName()).isEqualTo(account.getName());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getPhoneNumber()).isEqualTo(account.getPhoneNumber());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getEmail()).isEqualTo(account.getEmail());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getState()).isEqualTo(account.getState());
        assertThat(testEntityManager.find(Account.class, account.getIdx()).getNickname()).isEqualTo(account.getNickname());
    }
    @Test
    @DisplayName("삭제")
    @Order(4)
    void delete() {
        Account account = Account.builder()
                .userId("만석짱짱")
                .password("qwaskjc")
                .name("믹느")
                .phoneNumber("010-9415-3892")
                .email("qweas@naver.com")
                .state(State.ACTIVE)
                .nickname("qweasas")
                .build();

        testEntityManager.persist(account);
        testEntityManager.flush();
        testEntityManager.clear();

        accountRepository.delete(account);

        assertThat(accountRepository.findById(account.getIdx())).isEmpty();


    }

    @Test
    @DisplayName("Account를 userId로 조회")
    @Order(5)
    void findAccountByUserId() {
        Account account = Account.builder()                             
                .userId("만석짱짱")
                .password("qwaskjc")
                .name("믹느")
                .phoneNumber("010-9415-3892")
                .email("qweas@naver.com")
                .state(State.ACTIVE)
                .nickname("qweasas")
                .build();

        accountRepository.save(account);

        assertThat(accountRepository.findAccountByUserId(account.getUserId())).isPresent();
    }

    @Test
    @DisplayName("UserId로 AccountInformationDto조회")
    @Order(6)
    void findAccountInformationDtoByUserId() {
        Account account = Account.builder()
                .userId("만석짱짱")
                .password("qwaskjc")
                .name("믹느")
                .phoneNumber("010-9415-3892")
                .email("qweas@naver.com")
                .state(State.ACTIVE)
                .nickname("qweasas")
                .build();

        accountRepository.save(account);

        assertThat(accountRepository.findAccountInformationDtoByUserId(account.getUserId()).get().getUserId()).isEqualTo(account.getUserId());
        assertThat(accountRepository.findAccountInformationDtoByUserId(account.getUserId()).get().getPassword()).isEqualTo(account.getPassword());
        assertThat(accountRepository.findAccountInformationDtoByUserId(account.getUserId()).get().getEmail()).isEqualTo(account.getEmail());
    }

    @Test
    @DisplayName("AccountUpdateDto를 UserId로 조회")
    @Order(7)
    void findAccountUpdateDtoByUserId() {
        Account account = Account.builder()
                .userId("만석짱짱")
                .password("qwaskjc")
                .name("믹느")
                .phoneNumber("010-9415-3892")
                .email("qweas@naver.com")
                .state(State.ACTIVE)
                .nickname("qweasas")
                .build();

        accountRepository.save(account);

        assertThat(accountRepository.findAccountUpdateDtoByUserId(account.getUserId()).get().getUserId()).isEqualTo(account.getUserId());
        assertThat(accountRepository.findAccountUpdateDtoByUserId(account.getUserId()).get().getPassword()).isEqualTo(account.getPassword());
        assertThat(accountRepository.findAccountUpdateDtoByUserId(account.getUserId()).get().getName()).isEqualTo(account.getName());
        assertThat(accountRepository.findAccountUpdateDtoByUserId(account.getUserId()).get().getNickname()).isEqualTo(account.getNickname());
        assertThat(accountRepository.findAccountUpdateDtoByUserId(account.getUserId()).get().getEmail()).isEqualTo(account.getEmail());
        assertThat(accountRepository.findAccountUpdateDtoByUserId(account.getUserId()).get().getPhoneNumber()).isEqualTo(account.getPhoneNumber());
    }

    @Test
    @DisplayName("Email로 EmailResponseDto를 조회")
    @Order(8)
    void findEmailResponseDtoByEmail() {
        Account account = Account.builder()
                .userId("만석짱짱")
                .password("qwaskjc")
                .name("믹느")
                .phoneNumber("010-9415-3892")
                .email("qweas@naver.com")
                .state(State.ACTIVE)
                .nickname("qweasas")
                .build();

        accountRepository.save(account);

        assertThat(accountRepository.findEmailResponseDtoByEmail(account.getEmail()).get().getUserId()).isEqualTo(account.getUserId());
    }
}