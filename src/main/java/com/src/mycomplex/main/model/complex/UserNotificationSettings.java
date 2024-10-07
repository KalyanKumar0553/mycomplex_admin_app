package com.src.mycomplex.main.model.complex;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UserNotificationSettings {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Long unitID;
	
	private Long userID;
	
	private Boolean notifyDailyHelpMemberActivity;
	
	private Boolean notifyVehicleActivity;
	
	private Boolean notifyFrequentMemberActivity;
	
	private Boolean notifyFrequentVehicleActivity;
	
	private Boolean notifyPayments;
	
	private Boolean notifyHelpDeskTickets;
	
	private Boolean notifyAmenityBookings;
	
	private Boolean notifySocialFeeds;
	
}
