package com.onlinevet.clinic.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.onlinevet.clinic.model.Role;

public interface RoleReposiory extends CrudRepository<Role, Long> {
	Optional<Role> findByName(String name);
}
