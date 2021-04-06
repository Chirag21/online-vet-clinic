package com.onlinevet.clinic.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.onlinevet.clinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
	Owner findByFirstName(String firstName);

	Owner findByLastName(String lastName);
	
	List<Owner> findAllByFirstNameLike(String firstName);
	
	//List<Owner> findByFirstnameIgnoreCase(String firstName);
	
	List<Owner> findAllByLastNameLike(String lastName);
	
	//List<Owner> findByLastnameIgnoreCase(String lastName);
}	
