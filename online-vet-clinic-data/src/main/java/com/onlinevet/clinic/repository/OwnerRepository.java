package com.onlinevet.clinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinevet.clinic.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
	Owner findByFirstName(String firstName);

	Owner findByLastName(String lastName);
	
	List<Owner> findAllByFirstNameLike(String firstName);
	
	//List<Owner> findByFirstNameIgnoreCase(String firstName);
	
	List<Owner> findAllByLastNameLike(String lastName);
	
	//List<Owner> findByLastNameIgnoreCase(String lastName);
	
	List<Owner> findByTelephoneLike(String telephone);
	
}	
