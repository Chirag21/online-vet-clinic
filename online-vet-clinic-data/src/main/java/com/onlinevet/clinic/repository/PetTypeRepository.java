package com.onlinevet.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinevet.clinic.model.PetType;

public interface PetTypeRepository extends JpaRepository<PetType, Long> {
	
}
