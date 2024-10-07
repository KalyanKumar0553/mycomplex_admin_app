package com.src.mycomplex.main.repository.complex;


import org.springframework.data.jpa.repository.JpaRepository;

import com.src.mycomplex.main.model.complex.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    
}