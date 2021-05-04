package com.onlinevet.clinic.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.repository.OwnerRepository;
import com.onlinevet.clinic.repository.PetRepository;
import com.onlinevet.clinic.repository.PetTypeRepository;
import com.onlinevet.clinic.service.OwnerService;

@Service
@Profile("springdatajpa")
public class OwnerServiceImpl implements OwnerService {
	private final OwnerRepository ownerRepository;
	private final PetRepository petRepository;
	private final PetTypeRepository petTypeRepository;

	public OwnerServiceImpl(OwnerRepository ownerRepository, PetRepository petRepository,
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
	public List<Owner> findAllByLastNameLike(String lastName) {
		return ownerRepository.findAllByLastNameLike(lastName);
	}

	@Override
	public List<Owner> findAllByFirstNameLike(String firstName) {
		return ownerRepository.findAllByFirstNameLike(firstName);
	}

	@Override
	public List<Owner> findByTelephoneLike(String telephone) {
		List<Owner> owners = new ArrayList<>();
		ownerRepository.findByTelephoneLike(telephone).forEach(owners::add);
		return owners;
	}
	
	/*
	 * @Override public List<Owner> findByFirstNameIgnoreCase(String firstName) {
	 * return ownerRepository.findByFirstnameIgnoreCase(firstName); }
	 * 
	 * @Override public List<Owner> findByLastNameIgnoreCase(String lastName) {
	 * return ownerRepository.findByLastnameIgnoreCase(lastName); }
	 */

}
