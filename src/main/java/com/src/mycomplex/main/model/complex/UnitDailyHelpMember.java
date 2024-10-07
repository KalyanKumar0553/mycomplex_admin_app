package com.src.mycomplex.main.model.complex;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UnitDailyHelpMember {
	  
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	  
	private Long unitID;
	  
	private Long dailyHelpMemberId;
	
	private Long dailyHelpMemberTimeSlotID;
	  
	private Long addedBy;
	
	private Long addedOn;
	
	private Long lastModifiedBy;
	  
	private Long lastModifiedOn;
	
	private Boolean isActive;
}
