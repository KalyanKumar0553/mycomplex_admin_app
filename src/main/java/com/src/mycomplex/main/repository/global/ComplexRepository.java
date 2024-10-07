package com.src.mycomplex.main.repository.global;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.src.mycomplex.main.model.global.Complex;

public interface ComplexRepository extends JpaRepository<Complex, Long> {
	List<Complex> findByNameContainingIgnoreCase(String name);
}
