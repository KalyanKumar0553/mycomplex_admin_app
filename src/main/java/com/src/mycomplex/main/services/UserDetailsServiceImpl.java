package com.src.mycomplex.main.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;

import com.src.mycomplex.main.exceptions.InvalidRequestException;
import com.src.mycomplex.main.exceptions.OTPException;
import com.src.mycomplex.main.exceptions.SignupException;
import com.src.mycomplex.main.exceptions.UserNotFoundException;
import com.src.mycomplex.main.exceptions.UserRequestException;
import com.src.mycomplex.main.model.global.ComplexAdmin;
import com.src.mycomplex.main.model.global.UserInfo;
import com.src.mycomplex.main.repository.global.ComplexAdminRepository;
import com.src.mycomplex.main.repository.global.UserInfoRepository;
import com.src.mycomplex.main.request.dto.SignupRequestDTO;
import com.src.mycomplex.main.utils.PasswordUtil;
import com.src.mycomplex.main.utils.RequestStatus;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserInfoRepository userRepository;
	
	
	private final ComplexAdminRepository complexAdminRepository;

	private final MsgService emailService;

	private final OTPService otpService;
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = userRepository.findByEmailOrMobile(username, username)
				.orElseThrow(() -> new UserNotFoundException(RequestStatus.USER_NOT_FOUND));
		return new User(user.getUsername(), user.getPassword(), getAuthorities(user));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(UserInfo user) {
		return user.getAuthorities();
	}

	@Transactional
	public UserInfo saveUser(UserInfo user, PasswordEncoder passwordEncoder) {
		try {
			String salt = PasswordUtil.generateSalt();
			user.setSalt(salt);
			String hashPassword = PasswordUtil.hashPassword(user.getPassword(), salt);
			String encodedPassword = passwordEncoder.encode(hashPassword);
			user.setPassword(encodedPassword);
			String otp = generateOtp();
			user.setEnabled(false);
			Context context = new Context();
			context.setVariable("otp", otp);
			sendOTP(user,context);
			return userRepository.save(user);
		} catch(Exception e ) {
			throw new SignupException(RequestStatus.SIGNUP_SAVE_ERROR);
		}
	}
	
	public boolean sendOTP(UserInfo user,Context context) {
		if (otpService.canSendOtp(user.getUsername())) {
			if (user.getMobile() != null && !user.getMobile().isBlank()) {
				emailService.sendSMS("+19296006492", "+919148042308",
						"Please User OTP : " + context.getVariable("otp") + " To login MyComplex");
			} else {
				emailService.sendHtmlEmail("DoNotReply@8fcd10ba-82fa-40fa-88ff-892b2c57d4bd.azurecomm.net",
						user.getEmail(), "MyComplex : Verification Mail", "otpEmail", context);
			}
			otpService.saveOtp(user.getUsername(), (String)context.getVariable("otp"));
			otpService.recordOtpAttempt(user.getUsername());
			return true;
		} else {
			throw new OTPException(RequestStatus.OTP_LIMIT_EXCEED);
		}
	}

	public String generateOtp() {
		Random random = new Random();
		return String.format("%06d", random.nextInt(999999));
	}

	public Optional<UserInfo> findUserByUsername(String username) {
		return userRepository.findByEmailOrMobile(username, username);
	}

	public String verifyOtp(String username,String otp) {
		boolean isVerified = otpService.verifyOtp(username, otp);
		return (isVerified ? RequestStatus.OTP_VERIFICATION_SUCCESS : RequestStatus.OTP_VERIFICATION_FAIL).toString();
	}

	public String getUserName(SignupRequestDTO signupRequest) {
		return this.isMobileNumberProivded(signupRequest) ? signupRequest.getMobile() : signupRequest.getEmail();
	}

	public boolean isMobileNumberProivded(SignupRequestDTO signupRequest) {
		return signupRequest.getIsEmailSent() != null && !signupRequest.getIsEmailSent().booleanValue();
	}

	public void validateRequest(SignupRequestDTO signupRequest) {
		Boolean isEmailOrPassword = signupRequest.getIsEmailSent();
		if(isEmailOrPassword==null) {
			throw new InvalidRequestException(RequestStatus.SIGNUP_REQUEST_DATA_ERROR);
		}
		if (isEmailOrPassword.booleanValue()) {
			if(signupRequest.getEmail()!=null &&  !signupRequest.getEmail().trim().isEmpty()) {
				Optional<UserInfo> userWithEmail = userRepository.findByEmail(signupRequest.getEmail());
				if (userWithEmail.isPresent()) {
					throw new UserRequestException(RequestStatus.USER_DUPLICATE_EMAIL);
				}	
			} else {
				throw new UserRequestException(RequestStatus.SIGNUP_REQUEST_EMAIL_DATA_ERROR);
			}
		}
		if (!isEmailOrPassword.booleanValue()) {
			if(signupRequest.getMobile()!=null && !signupRequest.getMobile().trim().isEmpty()) {
				Optional<UserInfo> userWithMobile = userRepository.findByMobile(signupRequest.getMobile());
				if (userWithMobile.isPresent()) {
					throw new UserRequestException(RequestStatus.USER_DUPLICATE_MOBILE);
				}
			} else {
				throw new UserRequestException(RequestStatus.SIGNUP_REQUEST_MOBILE_DATA_ERROR);
			}
		}
	}

	public Set<GrantedAuthority> findAuthorities(Long userID) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		Optional<ComplexAdmin> complexAdmin = complexAdminRepository.findByUserID(userID);
		if(complexAdmin.isPresent()) {
			authorities.add(new SimpleGrantedAuthority("ADMIN"));
		}
		return authorities;
	}
}