package com.src.mycomplex.main.repository.global;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.src.mycomplex.main.model.global.Otp;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findFirstByUsernameOrderByCreatedAtDesc(String username);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Otp o WHERE o.username = :username")
    void deleteAllByUsername(String username);
}