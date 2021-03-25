package com.onlinevet.clinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.onlinevet.clinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
	
}
