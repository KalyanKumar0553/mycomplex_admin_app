package com.src.mycomplex.main.request.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
	private String email;
	private String mobile;
	private String password;
	private Boolean isEmailSent = false;
	private Boolean isAdmin = false;
}
