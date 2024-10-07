package com.src.mycomplex.main.configuration;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.src.mycomplex.main.filters.JwtAuthenticationFilter;
import com.src.mycomplex.main.services.UserDetailsServiceImpl;

import lombok.extern.log4j.Log4j2;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String LOGOUT_URL = "/logout";
	private static final String LOGOUT_SUCCESS_URL = "/logout";
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers("/assets/**","/*.js,/canvaskit/**","/icons/*.png","/index.html","/*.js","/*.json","/*.png","/",LOGOUT_URL)
				.permitAll().antMatchers("/api/auth/**").permitAll().antMatchers("/api/user/**").hasRole("USER")
				.antMatchers("/api/admin/**").hasRole("ADMIN").anyRequest().authenticated().and().logout()
		        .logoutUrl(LOGOUT_URL)
		        .logoutSuccessUrl(LOGOUT_SUCCESS_URL)
		        .deleteCookies("JSESSIONID")
		        .invalidateHttpSession(true)
		        .clearAuthentication(true)
		        .and()
				.exceptionHandling()
				.authenticationEntryPoint((request, response, authException) -> {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
				}).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
