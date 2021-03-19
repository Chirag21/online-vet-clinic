package com.onlinevet.clinic.services;

import java.util.Set;

import com.onlinevet.clinic.model.Owner;

public interface OwnerSerivce {
	Owner findbyId(Long id);

	Owner findByFirstName(String firstName);

	Owner findByLastName(String lastName);

	Owner save(Owner owner);

	Set<Owner> findAll();
}
