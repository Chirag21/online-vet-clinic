package com.onlinevet.clinic.springdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.repositories.OwnerRepository;
import com.onlinevet.clinic.repositories.PetRepository;
import com.onlinevet.clinic.repositories.PetTypeRepository;
import com.onlinevet.clinic.services.OwnerService;

@Service
@Profile("springdatajpa")
public class OwnerServiceJpa implements OwnerService {
	private final OwnerRepository ownerRepository;
	private final PetRepository petRepository;
	private final PetTypeRepository petTypeRepository;

	public OwnerServiceJpa(OwnerRepository ownerRepository, PetRepository petRepository,
			PetTypeRepository petTypeRepository) {
		this.ownerRepository = ownerRepository;
		this.petRepository = petRepository;
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public Owner findById(Long id) {
		return ownerRepository.findById(id).orElse(null);
	}

	@Override
	public Set<Owner> findAll() {
		Set<Owner> owners = new HashSet<>();
		ownerRepository.findAll().forEach(owners::add);
		return owners;
	}

	@Override
	public Owner save(Owner object) {
		System.out.println("JPA JPA JPA JPA JPA JPA JPA JPA JPA JPA");
		System.out.println("JPA JPA JPA JPA JPA JPA JPA JPA JPA JPA");
		System.out.println("JPA JPA JPA JPA JPA JPA JPA JPA JPA JPA");
		System.out.println("JPA JPA JPA JPA JPA JPA JPA JPA JPA JPA");
		System.out.println("JPA JPA JPA JPA JPA JPA JPA JPA JPA JPA");
		System.out.println("JPA JPA JPA JPA JPA JPA JPA JPA JPA JPA");
		return ownerRepository.save(object);
	}

	@Override
	public void delete(Owner object) {
		ownerRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		ownerRepository.deleteById(id);
	}

	@Override
	public Owner findByFirstName(String firstName) {
		return ownerRepository.findByFirstName(firstName);
	}

	@Override
	public Owner findByLastName(String lastName) {
		return ownerRepository.findByLastName(lastName);
	}

	
	@Override
	public List<Owner> findAllByLastNameLikeIgnoreCase(String lastName) {
		return ownerRepository.findAllByLastNameLikeIgnoreCase(lastName);
	}

	@Override
	public List<Owner> findAllByFirstNameLikeIgnoreCase(String firstName) {
		return ownerRepository.findAllByFirstNameLikeIgnoreCase(firstName);
	}
	
	/*
	 * @Override public List<Owner> findByFirstNameIgnoreCase(String firstName) {
	 * return ownerRepository.findByFirstnameIgnoreCase(firstName); }
	 * 
	 * @Override public List<Owner> findByLastNameIgnoreCase(String lastName) {
	 * return ownerRepository.findByLastnameIgnoreCase(lastName); }
	 */

}
