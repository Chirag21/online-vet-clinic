package com.onlinevet.clinic.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.onlinevet.clinic.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByUsername(String username);
	
	User findByEmail(String email);
	
	Optional<User> findById(Long id);
	
	boolean existsUserByUsername(String username);
	
	boolean existsUserByEmail(String email);
	
	public boolean existsById(Long id);
	
	public Optional<User> findByResetPasswordToken(String token);
	
	 public User getByResetPasswordToken(String token);
}
