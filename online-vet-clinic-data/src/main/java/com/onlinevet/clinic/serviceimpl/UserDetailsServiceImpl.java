package com.onlinevet.clinic.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.config.UserDetailsImpl;
import com.onlinevet.clinic.model.User;
import com.onlinevet.clinic.service.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userService.findByUsername(username);
		if(user.isPresent()) {
			return new UserDetailsImpl(user.get());
		}
		else {
			throw new UsernameNotFoundException("User not found with username = " + username);
		}
	}

}
