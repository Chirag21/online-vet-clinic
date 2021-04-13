package com.onlinevet.clinic.services;

import java.util.Optional;

import com.onlinevet.clinic.model.User;

public interface UserService extends CrudService<User, Long> {
	Optional<User> findByUsername(String username);
	
	User findById(Long id);

	boolean existsUserByUsername(String username);
	
	boolean existsUserByEmail(String email);
	
	public boolean existsById(Long id);
}
