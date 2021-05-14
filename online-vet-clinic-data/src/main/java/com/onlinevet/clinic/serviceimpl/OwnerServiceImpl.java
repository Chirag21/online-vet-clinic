package com.onlinevet.clinic.serviceimpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.onlinevet.clinic.model.UserRegistrationModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.model.User;
import com.onlinevet.clinic.repository.OwnerRepository;
import com.onlinevet.clinic.service.OwnerService;
import com.onlinevet.clinic.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class OwnerServiceImpl implements OwnerService {
	private static final String DEFAULT_OWNER_ROLE = "ROLE_OWNER";
	
	@Autowired
	private final OwnerRepository ownerRepository;

	@Autowired
	private final UserService userService;

	@Autowired
	private final ModelMapper modelMapper;

	@Override
	public void create(Owner owner) {
		UserRegistrationModel userRegistrationModel = modelMapper.map(owner, UserRegistrationModel.class);
		userRegistrationModel.setAdditionalRole(DEFAULT_OWNER_ROLE);
		User user = this.userService.register(userRegistrationModel);
		owner.setUser(user);
		this.ownerRepository.saveAndFlush(owner);
	}
	
	@Override
	public Owner findById(Long id) {
		return ownerRepository.findById(id).orElse(null);
	}

	@Override
	public Set<Owner> findAll() {
		return ownerRepository.findAll().stream().collect(Collectors.toSet());
	}

	@Override
	public Owner save(Owner owner) {
		return ownerRepository.saveAndFlush(owner);
	}

	@Override
	public void delete(Owner owner) {
		ownerRepository.delete(owner);
	}

	@Override
	public void deleteById(Long ownerId) {
		ownerRepository.deleteById(ownerId);
	}

	@Override
	public Owner findByFirstName(String firstName) {
		return ownerRepository.findByFirstName(firstName);
	}

	@Override
	public Owner findByLastName(String lastName) {
		return ownerRepository.findByLastName(lastName);
	}

	
	@Override
	public List<Owner> findAllByLastNameLike(String lastName) {
		return ownerRepository.findAllByLastNameLikeIgnoreCase(lastName);
	}

	@Override
	public List<Owner> findAllByFirstNameLike(String firstName) {
		return ownerRepository.findAllByFirstNameLikeIgnoreCase(firstName);
	}

	@Override
	public List<Owner> findByTelephoneLike(String telephone) {
		return ownerRepository.findByTelephoneLike(telephone)
					.stream()
					.collect(Collectors.toList());
	}

	@Override
	public Owner findByUserId(Long userId) {
		return ownerRepository.findByUserId(userId);
	}

}
