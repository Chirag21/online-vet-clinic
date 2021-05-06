package com.onlinevet.clinic.service;

import java.util.Optional;

import com.onlinevet.clinic.model.Role;

public interface RoleService extends CrudService<Role, Long> {
	Role getDefaultRole();
	
	Optional<Role> findByAuthority(String authority);
}
