package com.onlinevet.clinic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinevet.clinic.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsernameIgnoreCase(String username);
	
	User findByEmailIgnoreCase(String email);
	
	Optional<User> findById(Long id);
	
	boolean existsUserByUsernameIgnoreCase(String username);
	
	boolean existsUserByEmailIgnoreCase(String email);
	
	public boolean existsById(Long id);
	
	public Optional<User> findByResetPasswordToken(String token);
	
	 public User getByResetPasswordToken(String token);
}
