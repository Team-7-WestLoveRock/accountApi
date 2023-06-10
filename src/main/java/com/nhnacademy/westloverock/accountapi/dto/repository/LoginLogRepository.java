package com.nhnacademy.westloverock.accountapi.dto.repository;

import com.nhnacademy.westloverock.accountapi.entity.LoginLog;
import com.nhnacademy.westloverock.accountapi.response.LoginLogDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {
    Optional<LoginLogDto> findLoginLogByAccountIdx(Long index);

}
