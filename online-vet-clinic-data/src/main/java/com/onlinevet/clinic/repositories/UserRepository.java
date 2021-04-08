package com.onlinevet.clinic.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.onlinevet.clinic.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
