package com.src.mycomplex.main.utils;

import org.springframework.http.HttpStatus;

public enum RequestStatus {
	
	OTP_NOT_VERIFIED_ERROR(HttpStatus.BAD_REQUEST.value(),"User not verified OTP. Please verify OTP Sent to %s"),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(),"User not found"),
	SIGNUP_REQUEST_DATA_ERROR(HttpStatus.BAD_REQUEST.value(),"Invalid Request to Fetch Mobile / Email"),
	SIGNUP_REQUEST_EMAIL_DATA_ERROR(HttpStatus.BAD_REQUEST.value(),"Need Email To Register the account"),
	SIGNUP_REQUEST_MOBILE_DATA_ERROR(HttpStatus.BAD_REQUEST.value(),"Need Mobile To Register the account"),
	BAD_CREDENTIALS(HttpStatus.NOT_FOUND.value(),"Invalid Credentials"),
	SIGNUP_SAVE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Unable To Create the User"),
	USER_REGISTER_SUCCESS(HttpStatus.OK.value(), "User registered successfully. Please verify OTP sent to %s "),
	USER_DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST.value(),"User with email already taken. Please try with different email"),
	USER_DUPLICATE_MOBILE(HttpStatus.BAD_REQUEST.value(),"User with mobile already taken. Please try with different mobile"),
	OTP_SENT_SUCCESS(HttpStatus.OK.value(), "OTP Sent succesfully"),
	OTP_SENT_FAIL(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to send OTP"),
	OTP_LIMIT_EXCEED(HttpStatus.INTERNAL_SERVER_ERROR.value(), "OTP Limit exceeded."),
	OTP_TIME_LIMIT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "OTP Has been already sent please try after %d"),
	OTP_EXPIRATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "OTP Expired. Please enter latest OTP"),
	OTP_VERIFICATION_FAIL(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Unable to verify OTP. Please try again"),
	OTP_VERIFICATION_SUCCESS(HttpStatus.OK.value(),"OTP Verified succesfully"),
	EMAIL_SENT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Unable To Send OTP"),
	USER_ALREADY_VERIFY(HttpStatus.OK.value(), "User already verified.");
	
	private final int code;
	private final String description;

	private RequestStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getDescription(Object... params) {
		return String.format(description, params);
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code + ": " + description;
	}
}