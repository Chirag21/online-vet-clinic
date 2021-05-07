package com.onlinevet.clinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinevet.clinic.model.Vet;

public interface VetRepository extends JpaRepository<Vet, Long> {
	Vet findByFirstNameIgnoreCase(String firstName);
	
	Vet findByLastNameIgnoreCase(String lastName);
	
	Vet findByUserId(Long id);
	
	List<Vet> findAllByOrderByFirstNameAscLastName();
}
