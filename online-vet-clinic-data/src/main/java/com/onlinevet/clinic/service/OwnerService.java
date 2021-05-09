package com.onlinevet.clinic.service;

import java.util.List;

import com.onlinevet.clinic.model.Owner;


public interface OwnerService extends CrudService<Owner, Long> {
	void create(Owner owner);
	
	Owner findByFirstName(String firstName);

	Owner findByLastName(String lastName);
	
	Owner findByUserId(Long userId);
	
	List<Owner> findAllByFirstNameLike(String firstName);
	
	List<Owner> findAllByLastNameLike(String lastName);
	
	List<Owner> findByTelephoneLike(String telephone);
}
