package com.src.mycomplex.main.model.complex;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class DailyHelpMemberRatings {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Long dailyHelpMemberID;
	
	private Long userID;
	  
	private Long unitID;
	
	private Long rating;
	
	private String comment;
	
	private Long createdBY;
	
	private Date createOn;
	  
	private Long lastModifiedBy;
	
	private Date lastModifiedOn;

}
