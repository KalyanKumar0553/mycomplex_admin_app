package com.src.mycomplex.main.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.src.mycomplex.main.exceptions.OTPException;
import com.src.mycomplex.main.model.global.Otp;
import com.src.mycomplex.main.model.global.OtpAttempt;
import com.src.mycomplex.main.model.global.UserInfo;
import com.src.mycomplex.main.repository.global.OtpAttemptRepository;
import com.src.mycomplex.main.repository.global.OtpRepository;
import com.src.mycomplex.main.repository.global.UserInfoRepository;
import com.src.mycomplex.main.utils.RequestStatus;

@Service
public class OTPService {
	private static final int MAX_ATTEMPTS = 3;
	private static final int OTP_VALIDITY_MINUTES = 60;

	@Autowired
	private OtpRepository otpRepository;

	@Autowired
	private OtpAttemptRepository otpAttemptRepository;

	@Autowired
	private UserInfoRepository userRepository;

	public boolean canSendOtp(String username) {
		LocalDateTime today = LocalDateTime.now().toLocalDate().atStartOfDay();
		Optional<OtpAttempt> otpAttemptOpt = otpAttemptRepository.findByUsernameAndDate(username, today);
		if(otpAttemptOpt.isPresent()) {
			boolean isLessThanValidityMinutes = LocalDateTime.now().isBefore(otpAttemptOpt.get().getDate().plusMinutes(OTP_VALIDITY_MINUTES));
			if(isLessThanValidityMinutes) {
				throw new OTPException(RequestStatus.OTP_TIME_LIMIT_ERROR,ChronoUnit.MINUTES.between(LocalDateTime.now(), otpAttemptOpt.get().getDate().plusMinutes(OTP_VALIDITY_MINUTES)));
			}
		}
		return otpAttemptOpt.map(otpAttempt -> otpAttempt.getAttempts() < MAX_ATTEMPTS).orElse(true);
	}

	public void recordOtpAttempt(String username) {
		LocalDateTime today = LocalDateTime.now().toLocalDate().atStartOfDay();
		Optional<OtpAttempt> otpAttemptOpt = otpAttemptRepository.findByUsernameAndDate(username, today);
		if (otpAttemptOpt.isPresent()) {
			OtpAttempt otpAttempt = otpAttemptOpt.get();
			otpAttempt.setAttempts(otpAttempt.getAttempts() + 1);
			otpAttemptRepository.save(otpAttempt);
		} else {
			OtpAttempt otpAttempt = new OtpAttempt();
			otpAttempt.setUsername(username);
			otpAttempt.setDate(LocalDateTime.now());
			otpAttempt.setAttempts(1);
			otpAttemptRepository.save(otpAttempt);
		}
	}

	public String generateOtp() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}

	public void saveOtp(String username, String otp) {
		Otp otpEntity = new Otp();
		otpEntity.setUsername(username);
		otpEntity.setOtp(otp);
		otpEntity.setCreatedAt(LocalDateTime.now());
		otpRepository.save(otpEntity);
	}

	public boolean verifyOtp(String username, String otp) {
		Optional<Otp> otpOpt = otpRepository.findFirstByUsernameOrderByCreatedAtDesc(username);
		if (otpOpt.isPresent()) {
			Otp otpEntity = otpOpt.get();
			if (otpEntity.getOtp().equals(otp)) {
				Optional<UserInfo> user = userRepository.findByEmailOrMobile(username, username);
				user.ifPresent(u -> {
						u.setEnabled(true);
						userRepository.save(u);
						otpRepository.deleteAllByUsername(username);
						otpAttemptRepository.deleteAllByUsername(username);
				});
				return true;
			} else {
				throw new OTPException(RequestStatus.OTP_VERIFICATION_FAIL);
			}
		} else {
			throw new RuntimeException();
		}
	}
}
