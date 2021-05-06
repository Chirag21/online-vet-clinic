package com.onlinevet.clinic.serviceimpl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Role;
import com.onlinevet.clinic.repository.RoleReposiory;
import com.onlinevet.clinic.service.RoleService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class RoleServiceImpl implements RoleService{
	private static final String DEFAULT_ROLE = "ROLE_USER";
	
	@Autowired
	private final RoleReposiory roleRepository;
	
	@Override
	public Role findById(Long id) {
		return roleRepository.findById(id).orElseThrow();
	}

	@Override
	public Set<Role> findAll() {
		return roleRepository.findAll().stream().collect(Collectors.toSet());
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

	@Override
	public Role getDefaultRole() {
		return this.roleRepository.findByAuthority(DEFAULT_ROLE).orElseThrow();
	}

}
