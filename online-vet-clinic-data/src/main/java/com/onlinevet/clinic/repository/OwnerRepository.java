package com.onlinevet.clinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinevet.clinic.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
	Owner findByFirstName(String firstName);

	Owner findByLastName(String lastName);
	
	Owner findByUserId(Long userId);
	
	List<Owner> findAllByFirstNameLikeIgnoreCase(String firstName);
	
	List<Owner> findAllByLastNameLikeIgnoreCase(String lastName);
	
	List<Owner> findByTelephoneLike(String telephone);
	
}	
