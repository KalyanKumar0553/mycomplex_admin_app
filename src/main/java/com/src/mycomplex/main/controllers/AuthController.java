package com.src.mycomplex.main.controllers;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

import com.src.mycomplex.main.configuration.JWTTokenProvider;
import com.src.mycomplex.main.exceptions.InvalidRequestException;
import com.src.mycomplex.main.exceptions.UserNotFoundException;
import com.src.mycomplex.main.model.global.UserInfo;
import com.src.mycomplex.main.request.dto.LoginRequestDTO;
import com.src.mycomplex.main.request.dto.SignupRequestDTO;
import com.src.mycomplex.main.response.dto.JSONResponseDTO;
import com.src.mycomplex.main.response.dto.JwtAuthenticationResponseDTO;
import com.src.mycomplex.main.services.UserDetailsServiceImpl;
import com.src.mycomplex.main.utils.AppUtils;
import com.src.mycomplex.main.utils.PasswordUtil;
import com.src.mycomplex.main.utils.RequestStatus;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTTokenProvider tokenProvider;

	@Autowired
	private UserDetailsServiceImpl userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity<JSONResponseDTO<?>> authenticateUser(@RequestBody LoginRequestDTO loginRequest) {
		String username = loginRequest.getIsEmailSent() ? loginRequest.getEmail() : loginRequest.getMobile();
		String password = loginRequest.getPassword();
		UserInfo user = userService.findUserByUsername(username)
				.orElseThrow(() -> new UserNotFoundException(RequestStatus.USER_NOT_FOUND));
		if (!user.isEnabled()) {
			String usernameSrc = user.getUsername().equalsIgnoreCase(user.getEmail()) ? "email" : "mobile";
			log.info("usernameSrc : {}", usernameSrc);
			throw new UserNotFoundException(RequestStatus.OTP_NOT_VERIFIED_ERROR, usernameSrc);
		}
		String hashPassword = PasswordUtil.hashPassword(password, user.getSalt());
		String encodedPassword = passwordEncoder.encode(hashPassword);
		if (loginRequest.getIsAdmin() != null) {

		}
		if (!passwordEncoder.matches(hashPassword, encodedPassword)) {
			throw new UserNotFoundException(RequestStatus.BAD_CREDENTIALS);
		}
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				user.getUsername(), hashPassword, userService.findAuthorities(user.getId()));
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		String token = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(AppUtils.getJSONObject(new JwtAuthenticationResponseDTO(token)));
	}

	@PostMapping("/signup")
	public ResponseEntity<JSONResponseDTO<?>> registerUser(@RequestBody SignupRequestDTO signupRequest)
			throws MessagingException {
		if ((signupRequest.getEmail() == null || signupRequest.getEmail().isEmpty())
				&& (signupRequest.getMobile() == null || signupRequest.getMobile().isEmpty())) {
			throw new InvalidRequestException(RequestStatus.SIGNUP_REQUEST_DATA_ERROR);
		}
		userService.validateRequest(signupRequest);
		String msgSource = userService.isMobileNumberProivded(signupRequest) ? "mobile" : "email";
		UserInfo userInfo = UserInfo.builder().mobile(signupRequest.getMobile()).email(signupRequest.getEmail())
				.username(userService.getUserName(signupRequest)).password(signupRequest.getPassword()).build();
		userService.saveUser(userInfo, passwordEncoder);
		String msg = String.format(RequestStatus.USER_REGISTER_SUCCESS.getDescription(msgSource));
		return ResponseEntity.ok(AppUtils.getJSONObject(msg));
	}

	@PostMapping("/verify")
	public ResponseEntity<JSONResponseDTO<?>> verifyOtp(@RequestBody SignupRequestDTO otpVerificationRequest) {
		if ((otpVerificationRequest.getEmail() == null || otpVerificationRequest.getEmail().isEmpty())
				&& (otpVerificationRequest.getMobile() == null || otpVerificationRequest.getMobile().isEmpty())) {
			throw new InvalidRequestException(RequestStatus.SIGNUP_REQUEST_DATA_ERROR);
		}
		String username = userService.isMobileNumberProivded(otpVerificationRequest)
				? otpVerificationRequest.getMobile()
				: otpVerificationRequest.getEmail();
		UserInfo user = userService.findUserByUsername(username)
				.orElseThrow(() -> new UserNotFoundException(RequestStatus.USER_NOT_FOUND));
		if (user.isEnabled()) {
			return ResponseEntity.ok(AppUtils.getJSONObject(RequestStatus.USER_ALREADY_VERIFY.getDescription()));
		}
		String msg = userService.verifyOtp(username, otpVerificationRequest.getOtp());
		return ResponseEntity.ok(AppUtils.getJSONObject(msg));
	}

	@PostMapping("/send_otp")
	public ResponseEntity<JSONResponseDTO<?>> sendOtp(@RequestBody SignupRequestDTO signupRequest) {
		if ((signupRequest.getEmail() == null || signupRequest.getEmail().isEmpty())
				&& (signupRequest.getMobile() == null || signupRequest.getMobile().isEmpty())) {
			throw new InvalidRequestException(RequestStatus.SIGNUP_REQUEST_DATA_ERROR);
		}
		String username = signupRequest.getIsEmailSent() ? signupRequest.getEmail() : signupRequest.getMobile();
		UserInfo user = userService.findUserByUsername(username)
				.orElseThrow(() -> new UserNotFoundException(RequestStatus.USER_NOT_FOUND));
		Context context = new Context();
		context.setVariable("otp", userService.generateOtp());
		boolean otpSent = userService.sendOTP(user, context);
		return ResponseEntity.ok(AppUtils.getJSONObject(otpSent ? RequestStatus.OTP_SENT_SUCCESS.getDescription()
					: RequestStatus.OTP_SENT_FAIL.getDescription()));
	}
	
	@PostMapping("/logout")
	public ResponseEntity<JSONResponseDTO<?>> logoutSuccess(HttpServletRequest request) {
		return ResponseEntity.ok(JSONResponseDTO.builder().statusMsg("User Loggedout").isError(false).build());
	}
}