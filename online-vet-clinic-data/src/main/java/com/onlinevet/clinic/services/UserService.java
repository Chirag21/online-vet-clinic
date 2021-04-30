package com.onlinevet.clinic.services;

import java.util.Optional;

import com.onlinevet.clinic.model.User;

public interface UserService extends CrudService<User, Long> {
	Optional<User> findByUsername(String username);
	
	User findByEmail(String email);
	
	User findById(Long id);

	boolean existsUserByUsername(String username);
	
	boolean existsUserByEmail(String email);
	
	public boolean existsById(Long id);
	
	public Optional<User> findByResetPasswordToken(String token);
	
	public void updateResetPasswordToken(String token, String email);
	
	public void updatePassword(User user, String newPassword);

	 public User getByResetPasswordToken(String token);
}
