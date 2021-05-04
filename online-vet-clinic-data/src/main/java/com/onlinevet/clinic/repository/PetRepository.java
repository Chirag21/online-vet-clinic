package com.onlinevet.clinic.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinevet.clinic.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
	Pet findByName(String name);
	
	List<Pet> findAllByVetId(Long vetId);

    Page<Pet> findAllByVetIdOrderByBirthDateDesc(Long vetId, Pageable pageable);

}
