package com.onlinevet.clinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.onlinevet.clinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {
	public Pet findByName(String name);
}
