package com.onlinevet.clinic.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Pet;
import com.onlinevet.clinic.services.PetService;

@Service
public class PetServiceMapImpl extends AbstractMapService<Pet, Long> implements PetService {

	public Pet findById(Long id) {
		return super.findById(id);
	}

	public Set<Pet> findAll(){
		return super.findAll();
	}

	public Pet save(Pet object) {
		return super.save(object.getId(), object);
	}

	public void delete(Pet object) {
		super.delete(object);
	}

	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public Pet findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
