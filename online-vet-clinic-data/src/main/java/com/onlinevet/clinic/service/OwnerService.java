package com.onlinevet.clinic.service;

import java.util.List;

import com.onlinevet.clinic.model.Owner;


public interface OwnerService extends CrudService<Owner, Long> {
	Owner findByFirstName(String firstName);

	Owner findByLastName(String lastName);

	List<Owner> findAllByFirstNameLike(String firstName);
	
	//List<Owner> findByFirstNameIgnoreCase(String firstName);
	
	List<Owner> findAllByLastNameLike(String lastName);
	
	//List<Owner> findByLastNameIgnoreCase(String lastName);
	
	List<Owner> findByTelephoneLike(String telephone);
}
