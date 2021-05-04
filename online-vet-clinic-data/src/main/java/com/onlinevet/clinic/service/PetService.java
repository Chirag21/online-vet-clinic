package com.onlinevet.clinic.service;

import com.onlinevet.clinic.model.Pet;

public interface PetService extends CrudService<Pet, Long>{
	Pet findByName(String name);
}
