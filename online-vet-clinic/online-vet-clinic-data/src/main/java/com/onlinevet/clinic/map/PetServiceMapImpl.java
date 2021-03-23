package com.onlinevet.clinic.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Pet;
import com.onlinevet.clinic.services.PetService;

@Service
public class PetServiceMapImpl extends AbstractMapService<Pet, Long> implements PetService {

	@Override
	public Pet findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Set<Pet> findAll(){
		return super.findAll();
	}

	@Override
	public Pet save(Pet object) {
		return super.save(object.getId(), object);
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
