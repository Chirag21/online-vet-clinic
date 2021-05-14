package com.onlinevet.clinic.serviceimpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Pet;
import com.onlinevet.clinic.repository.PetRepository;
import com.onlinevet.clinic.service.PetService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Profile({"default","springdatajpa"})
public class PetServiceImpl implements PetService{
	@Autowired
	private final PetRepository petRepository;
	
	@Override
	public Pet findById(Long id) {
		return petRepository.findById(id).orElse(null);
	}

	@Override
	public Set<Pet> findAll() {
		return petRepository.findAll().stream().collect(Collectors.toSet());
	}

	@Override
	public Pet save(Pet pet) {
		return petRepository.save(pet);
	}

	@Override
	public void delete(Pet pet) {
		petRepository.delete(pet);
	}

	@Override
	public void deleteById(Long id) {
		petRepository.deleteById(id);
	}

	@Override
	public Pet findByName(String name) {
		return petRepository.findByNameIgnoreCase(name);
	}

	@Override
	public List<Pet> findAllByVetId(Long vetId) {
		return petRepository.findAllByVetId(vetId);
	}

	@Override
	public Page<Pet> findAllByVetIdOrderByBirthDateDesc(Long vetId, Pageable pageable) {
		Page<Pet> pets = petRepository.findAll(pageable);
		return new PageImpl<>(pets.toList(), pageable, pets.getTotalElements());
	}

	@Override
	public Pet findByOwnerId(Long ownerId) {
		return petRepository.findByOwnerId(ownerId);
	}

	@Override
	public List<Pet> findAllByOwnerId(Long ownerId) {
		return petRepository.findAllByOwnerId(ownerId);
	}

	@Override
	public Page<Pet> findAllByOwnerIdOrderByBirthDateDesc(Long ownerId, Pageable pageable) {
		return petRepository.findAllByOwnerIdOrderByBirthDateDesc(ownerId, pageable);
	}

	@Override
	public Page<Pet> findAllByOrderByName(Pageable pageable) {
		return petRepository.findAllByOrderByName(pageable);
	}
	
}
