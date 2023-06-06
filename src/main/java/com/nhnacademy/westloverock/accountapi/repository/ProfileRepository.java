package com.nhnacademy.westloverock.accountapi.repository;

import com.nhnacademy.westloverock.accountapi.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long>, ProfileRepositoryCustom {
}
