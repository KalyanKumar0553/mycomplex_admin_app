package com.src.mycomplex.main.model.complex;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UnitPets {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Long unitID;
	
	private Long petID;
	
	private String name;
	
	private Boolean isVaccinated;
	  
	private Long createBy;
	  
	private Date createOn;
	  
	private Long lastModifiedBy;
	  
	private Date lastModifiedOn;
}
