package com.nhnacademy.westloverock.accountapi.repository;

import com.nhnacademy.westloverock.accountapi.entity.LoginLog;
import com.nhnacademy.westloverock.accountapi.dto.response.LoginLogDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {
    Optional<LoginLogDto> findLoginLogByAccount_UserId(String userId);

}
