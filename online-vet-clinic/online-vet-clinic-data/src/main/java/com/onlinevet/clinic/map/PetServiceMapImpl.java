package com.onlinevet.clinic.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Pet;
import com.onlinevet.clinic.services.PetService;
import com.onlinevet.clinic.services.PetTypeService;

@Service
public class PetServiceMapImpl extends AbstractMapService<Pet, Long> implements PetService {

	PetTypeService petTypeService;

	public PetServiceMapImpl(PetTypeService petTypeService) {
		super();
		this.petTypeService = petTypeService;
	}

	@Override
	public Pet findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Set<Pet> findAll() {
		return super.findAll();
	}

	@Override
	public Pet save(Pet object) {
		return super.save(object);
	}

	@Override
	public void delete(Pet object) {
		super.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public Pet findByName(String name) {
		return null;
	}

}
