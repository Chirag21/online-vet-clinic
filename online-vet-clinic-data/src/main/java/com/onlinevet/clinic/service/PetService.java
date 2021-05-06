package com.onlinevet.clinic.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.onlinevet.clinic.model.Pet;

public interface PetService extends CrudService<Pet, Long>{
	Pet findByName(String name);
	
	List<Pet> findAllByVetId(Long vetId);

    Page<Pet> findAllByVetIdOrderByBirthDateDesc(Long vetId, Pageable pageable);

    Pet findByOwnerId(Long ownerId);
    
    List<Pet> findAllByOwnerId(Long ownerId);
}
