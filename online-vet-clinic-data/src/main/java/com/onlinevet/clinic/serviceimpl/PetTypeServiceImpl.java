package com.onlinevet.clinic.serviceimpl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.PetType;
import com.onlinevet.clinic.repository.PetTypeRepository;
import com.onlinevet.clinic.service.PetTypeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class PetTypeServiceImpl implements PetTypeService{
	@Autowired
	private final PetTypeRepository petTypeRepository;

	@Override
	public PetType findById(Long id) {
		return petTypeRepository.findById(id).orElse(null);
	}

	@Override
	public Set<PetType> findAll() {
		return petTypeRepository.findAll().stream().collect(Collectors.toSet());
	}

	@Override
	public PetType save(PetType petType) {
		return petTypeRepository.saveAndFlush(petType);
	}

	@Override
	public void delete(PetType petType) {
		petTypeRepository.delete(petType);
	}

	@Override
	public void deleteById(Long id) {
		petTypeRepository.deleteById(id);
	}
}
