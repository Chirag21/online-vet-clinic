package com.onlinevet.clinic.services;

import java.util.Optional;

import com.onlinevet.clinic.model.Role;

public interface RoleService extends CrudService<Role, Long> {
	Optional<Role> findByName(String name);
}
