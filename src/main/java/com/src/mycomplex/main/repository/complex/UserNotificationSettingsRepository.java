package com.src.mycomplex.main.repository.complex;


import org.springframework.data.jpa.repository.JpaRepository;
import com.src.mycomplex.main.model.complex.UserNotificationSettings;

public interface UserNotificationSettingsRepository extends JpaRepository<UserNotificationSettings, Long> {
    
}