package com.onlinevet.clinic.serviceimpl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Visit;
import com.onlinevet.clinic.repository.VisitRepository;
import com.onlinevet.clinic.service.VisitService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class VisitServiceImpl implements VisitService {
	@Autowired
	private final VisitRepository visitRepository;

	@Override
	public Visit findById(Long id) {
		return visitRepository.findById(id).orElse(null);
	}

	@Override
	public Set<Visit> findAll() {
		return visitRepository.findAll().stream().collect(Collectors.toSet());
	}

	@Override
	public Visit save(Visit visit) {
		return visitRepository.saveAndFlush(visit);
	}

	@Override
	public void delete(Visit visit) {
		visitRepository.delete(visit);
	}

	@Override
	public void deleteById(Long id) {
		visitRepository.deleteById(id);
	}

}
