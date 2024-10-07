package com.src.mycomplex.main.model.complex;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UserUnitFamily {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Long userID;

	private Long unitID;
	
	private String name;
	
	private Date createdOn;
	
	private Long createdBy;
	
	private Date lastModifiedOn;
	
	private Long lastModifiedBy;

}
