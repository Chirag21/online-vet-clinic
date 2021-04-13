package com.onlinevet.clinic.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.onlinevet.clinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
	Owner findByFirstName(String firstName);

	Owner findByLastName(String lastName);
	
	List<Owner> findAllByFirstNameLikeIgnoreCase(String firstName);
	
	//List<Owner> findByFirstNameIgnoreCase(String firstName);
	
	List<Owner> findAllByLastNameLikeIgnoreCase(String lastName);
	
	//List<Owner> findByLastNameIgnoreCase(String lastName);
	
	List<Owner> findByTelephoneLike(String telephone);
}	
