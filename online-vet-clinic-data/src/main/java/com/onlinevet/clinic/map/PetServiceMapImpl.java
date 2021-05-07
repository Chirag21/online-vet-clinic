package com.onlinevet.clinic.map;

import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Pet;
import com.onlinevet.clinic.service.PetService;

@Service
@Profile({ "map" })
public class PetServiceMapImpl extends AbstractMapService<Pet, Long> implements PetService {

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

	@Override
	public Pet findByOwnerId(Long ownerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pet> findAllByVetId(Long vetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Pet> findAllByVetIdOrderByBirthDateDesc(Long vetId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pet> findAllByOwnerId(Long ownerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
