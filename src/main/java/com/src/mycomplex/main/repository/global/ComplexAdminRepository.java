package com.src.mycomplex.main.repository.global;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.src.mycomplex.main.model.global.ComplexAdmin;

public interface ComplexAdminRepository extends JpaRepository<ComplexAdmin, Long> {
	Optional<ComplexAdmin> findByUserID(Long userID);
}
