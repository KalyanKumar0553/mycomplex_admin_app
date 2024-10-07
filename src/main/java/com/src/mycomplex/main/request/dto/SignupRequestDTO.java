package com.src.mycomplex.main.request.dto;

import lombok.Data;

@Data
public class SignupRequestDTO {
	private String email;
	private String mobile;
	private String password;
	private Boolean isEmailSent = false;
	private String otp;
}
