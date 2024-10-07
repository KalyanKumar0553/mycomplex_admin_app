package com.src.mycomplex.main.model.complex;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UserUnityFamilyMember {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Long userUnitFamilyID;
	
	private Long userID;
	
	private Long addedBy;
	
	private Date addedOn;
	
	private Long lastModifiedBy;
	
	private Date lastModifiedOn;
}
