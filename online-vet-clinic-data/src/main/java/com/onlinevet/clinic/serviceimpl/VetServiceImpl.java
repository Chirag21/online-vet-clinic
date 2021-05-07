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

import com.onlinevet.clinic.model.User;
import com.onlinevet.clinic.model.Vet;
import com.onlinevet.clinic.model.WeekSchedule;
import com.onlinevet.clinic.repository.VetRepository;
import com.onlinevet.clinic.service.UserService;
import com.onlinevet.clinic.service.VetService;
import com.onlinevet.clinic.service.WeekScheduleService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class VetServiceImpl implements VetService {
	private static final String DEFAULT_VET_ROLE = "ROLE_VET";
	
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
	public Vet save(Vet vet) {
		return vetRepository.saveAndFlush(vet);
	}

	@Override
	public void delete(Vet vet) {
		vetRepository.delete(vet);
	}

	@Override
	public void deleteById(Long id) {
		vetRepository.deleteById(id);
	}

	@Override
	public Vet findByFirstName(String firstName) {
		return vetRepository.findByFirstNameIgnoreCase(firstName);
	}

	@Override
	public Vet findByLastName(String lastName) {
		return vetRepository.findByLastNameIgnoreCase(lastName);
	}

	@Override
	public Vet findByUserId(Long id) {
		return vetRepository.findByUserId(id);
	}

	@Override
	public List<Vet> findAllByOrderByFirstNameAscLastName() {
		return vetRepository.findAllByOrderByFirstNameAscLastName();
	}

	@Override
	public void create(Vet vet) {
		User user = createVetUser(vet);
		WeekSchedule weekSchedule = weekScheduleService.createDefault();
		vet.setUser(user);
		vet.setWeekSchedule(weekSchedule);
		vetRepository.saveAndFlush(vet);
	}

	@Override
	public void savePicture(Vet vet) {
		vetRepository.saveAndFlush(vet);
	}
	
	@Override
	public void savePicture(Vet vet, String picturePath) {
		vet.setPicturePath(picturePath);
		vetRepository.save(vet);
	}

	@Override
	public List<Vet> findAllSelect() {
		return vetRepository.findAllByOrderByFirstNameAscLastName();
	}

	@Override
	public Page<Vet> findAll(Pageable pageable) {
        Page<Vet> vets = vetRepository.findAll(pageable);
        return new PageImpl<>(vets.toList(), pageable, vets.getTotalElements());
	}

	public User createVetUser(Vet vet) {
        vet.setAdditionalRole(DEFAULT_VET_ROLE);
        return userService.register(vet);
	}
}
