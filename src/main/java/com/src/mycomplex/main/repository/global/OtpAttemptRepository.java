package com.src.mycomplex.main.repository.global;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.src.mycomplex.main.model.global.OtpAttempt;

public interface OtpAttemptRepository extends JpaRepository<OtpAttempt, Long> {
	Optional<OtpAttempt> findByUsernameAndDate(String username, LocalDateTime date);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM OtpAttempt o WHERE o.username = :username")
	void deleteAllByUsername(String username);
}