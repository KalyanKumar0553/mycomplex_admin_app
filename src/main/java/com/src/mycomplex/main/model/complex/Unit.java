package com.src.mycomplex.main.model.complex;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Unit {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Long complexID;
	
	private Long complexBlockID;
	
	private Long complexBlockFloorID;
	
	private Long parkingSlotID;
	
	private UnitType type;
	
	private Long builArea;
	
	private Long floorArea;
	
	private Long uds;
	
	private AreaMeasure measure;
	
	private Boolean isOccupied;
	
	private String createdBy;
	
	private String lastModifiedBy;
	
	private Date createdOn;
	
	private Date lastModifiedOn;
}
