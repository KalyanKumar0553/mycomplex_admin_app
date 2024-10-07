package com.src.mycomplex.main.model.complex;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ComplexBlockFloor {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private Long complexBlockID;
	
	private String name;
	
	private Boolean isParkable;
	
}
