package com.onlinevet.clinic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinevet.clinic.model.Role;

public interface RoleReposiory extends JpaRepository<Role, Long> {
	Optional<Role> findByAuthorityIgnoreCase(String authority);
}
