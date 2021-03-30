package com.onlinevet.clinic.services;

import com.onlinevet.clinic.model.Pet;

public interface PetService extends CrudService<Pet, Long>{
	Pet findByName(String name);
}
