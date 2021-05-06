package com.onlinevet.clinic.serviceimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Vet;
import com.onlinevet.clinic.repository.VetRepository;
import com.onlinevet.clinic.service.UserService;
import com.onlinevet.clinic.service.VetService;
import com.onlinevet.clinic.service.WeekScheduleService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class VetServiceImpl implements VetService {
	@Autowired
	private final VetRepository vetRepository;

	@Autowired
	private final UserService userService;
	
	@Autowired
	 private WeekScheduleService weekScheduleService;
	
	@Override
	public Vet findById(Long id) {
		return vetRepository.findById(id).orElse(null);
	}

	@Override
	public Set<Vet> findAll() {
		return vetRepository.findAll().stream().collect(Collectors.toSet());
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

	@Override
	public Vet findByUserId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vet> findAllByOrderByFirstNameAscLastName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Vet vet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePicture(Vet vet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePicture(String picturPath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Vet> findAllSelect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Vet> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public void createVetUser(Vet vet) {
        String DEFAULT_DOCTOR_ROLE = "ROLE_DOCTOR";
        vet.setAdditionalRole(DEFAULT_DOCTOR_ROLE);
        return this.userService.register(vet);
	}

}
