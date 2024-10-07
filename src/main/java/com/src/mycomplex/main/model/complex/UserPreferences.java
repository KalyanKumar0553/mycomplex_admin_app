package com.src.mycomplex.main.model.complex;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UserPreferences {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Long unitID;
	
	private Long userID;
	
	private Boolean enableVrCalling;
	
	private Boolean   payRentThroughApp;
	
}
