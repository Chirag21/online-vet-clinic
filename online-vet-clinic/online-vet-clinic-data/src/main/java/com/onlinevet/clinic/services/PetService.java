package com.onlinevet.clinic.services;

import java.util.Set;

import com.onlinevet.clinic.model.Pet;

public interface PetService {
	Pet findById(Long id);

	Pet save(Pet pet);

	Set<Pet> findAll();
}
