package com.onlinevet.clinic.serviceimpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Role;
import com.onlinevet.clinic.repository.RoleReposiory;
import com.onlinevet.clinic.service.RoleService;

@Service
@Profile("springdatajpa")
public class RoleServiceImpl implements RoleService{

	RoleReposiory roleRepository;
	
	@Override
	public Role findById(Long id) {
		return roleRepository.findById(id).orElse(new Role());
	}

	@Override
	public Set<Role> findAll() {
		Set<Role> roles = new HashSet<>();
		roleRepository.findAll().forEach(roles :: add);
		return roles;
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void delete(Role role) {
		roleRepository.delete(role);
	}

	@Override
	public void deleteById(Long id) {
		roleRepository.deleteById(id);
	}

	@Override
	public Optional<Role> findByAuthority(String authority) {
		return roleRepository.findByAuthority(authority);
	}

}
