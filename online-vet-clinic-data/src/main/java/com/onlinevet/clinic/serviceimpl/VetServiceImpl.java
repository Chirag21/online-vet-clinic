package com.onlinevet.clinic.serviceimpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Vet;
import com.onlinevet.clinic.repository.VetRepository;
import com.onlinevet.clinic.service.VetService;

@Service
@Profile("springdatajpa")
public class VetServiceImpl implements VetService {
	private final VetRepository vetRepository;

	public VetServiceImpl(VetRepository vetRepository) {
		super();
		this.vetRepository = vetRepository;
	}

	@Override
	public Vet findById(Long id) {
		return vetRepository.findById(id).orElse(null);
	}

	@Override
	public Set<Vet> findAll() {
		Set<Vet> vets = new HashSet<>();
		vetRepository.findAll().forEach(vets::add);
		return vets;
	}

	@Override
	public Vet save(Vet object) {
		return vetRepository.save(object);
	}

	@Override
	public void delete(Vet object) {
		vetRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		vetRepository.deleteById(id);
	}

	@Override
	public Vet findByFirstName(String firstName) {
		return vetRepository.findByFirstName(firstName);
	}

	@Override
	public Vet findByLastName(String lastName) {
		return vetRepository.findByLastName(lastName);
	}

}
