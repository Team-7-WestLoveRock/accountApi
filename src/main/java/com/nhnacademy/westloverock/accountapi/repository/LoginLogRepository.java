package com.nhnacademy.westloverock.accountapi.repository;

import com.nhnacademy.westloverock.accountapi.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogRepository extends JpaRepository<LoginLog, Long>, LoginLogRepositoryCustom{
}
