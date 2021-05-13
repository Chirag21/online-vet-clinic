package com.onlinevet.clinic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.onlinevet.clinic.serviceimpl.UserDetailsServiceImpl;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String VET = "VET";
	private static final String OWNER = "OWNER";
	private static final String USER = "USER";
	private static final String ADMIN = "ADMIN";
	private static final String LOGIN = "/";

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public DaoAuthenticationProvider getDaoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(getBCryptPasswordEncoder());
		return daoAuthenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getDaoAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(LOGIN, "/register-owner", "/forgotPassword",
						 "/register-vet", "/vets/**", "/mm_pics/**",
                         "/bootstrap/**", "/jquery/**", "/tether/**", "/font-awesome/**", "/select2/**", "/css/**",
                         "/img/**", "/connect/**", "/error/**","/h2/**").permitAll()
			.antMatchers("/appointments/home","/appointments/vet/appointments","/appointments/appointment/appointment"
					,"/pet/**","/appointments/appointment/add").hasAnyRole(ADMIN,USER,OWNER,VET)
			.antMatchers("/appointment/vet/**", "/schedule/edit", "/vet/edit", "/vet/pets, /vet/edit-picture","/pet/pets"
					,"/appointments/vet/**").hasAnyRole(ADMIN,VET)
            .antMatchers("/appointments/pet/appointments","/appointment/pet/**", "/pet/edit","/owners/**","/mypets"
            		,"/appointments/pet/**").hasAnyRole(ADMIN,OWNER)
			.antMatchers( "/index" ,"/pets/**").hasRole(ADMIN)
			.antMatchers("/**").hasRole(ADMIN)
			.anyRequest().authenticated()
			.and().formLogin()
			.loginPage(LOGIN).loginProcessingUrl("/login").defaultSuccessUrl("/appointments/home", false)
			// .failureUrl("/loginSignup?failure")
			.and().csrf().disable().logout().logoutSuccessUrl(LOGIN).and().exceptionHandling()
			.accessDeniedPage("/accessDenied");
		http.headers().frameOptions().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		 web
         .ignoring()
         .antMatchers("/resources/**", "/bootstrap/**" , "/static/**", "/css/**", "/js/**", "/images/**", "/icon/**");
	}
}
