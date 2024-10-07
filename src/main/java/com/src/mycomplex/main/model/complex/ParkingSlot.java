package com.src.mycomplex.main.model.complex;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ParkingSlot {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String parkingBlock;
	
	private Long complexBlockFloorID;

	private Boolean isAvailed;
	
	private Long parkingLotNumber;
}
