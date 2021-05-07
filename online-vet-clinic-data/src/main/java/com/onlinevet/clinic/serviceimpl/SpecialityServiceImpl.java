package com.onlinevet.clinic.serviceimpl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Speciality;
import com.onlinevet.clinic.repository.SpecialityRepository;
import com.onlinevet.clinic.service.SpecialityService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class SpecialityServiceImpl implements SpecialityService {
	@Autowired
	private final SpecialityRepository specialityRepository;

	@Override
	public Speciality findById(Long id) {
		return specialityRepository.findById(id).orElse(null);
	}

	@Override
	public Set<Speciality> findAll() {
		return specialityRepository.findAll().stream().collect(Collectors.toSet());
	}

	@Override
	public Speciality save(Speciality speciality) {
		return specialityRepository.saveAndFlush(speciality);
	}

	@Override
	public void delete(Speciality speciality) {
		specialityRepository.delete(speciality);
	}

	@Override
	public void deleteById(Long id) {
		specialityRepository.deleteById(id);
	}

}
