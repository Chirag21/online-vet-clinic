package com.onlinevet.clinic.services;

import java.util.Optional;

import com.onlinevet.clinic.model.User;

public interface UserService extends CrudService<User, Long> {
	Optional<User> findByUserName(String userName);
}
