package com.src.mycomplex.main.repository.global;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.src.mycomplex.main.model.global.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	Optional<UserInfo> findByEmail(String email);
	Optional<UserInfo> findByMobile(String mobile);
    Optional<UserInfo> findByEmailOrMobile(String email,String mobile);
}
