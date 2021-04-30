package com.onlinevet.clinic.serviceimpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Visit;
import com.onlinevet.clinic.repositories.VisitRepository;
import com.onlinevet.clinic.services.VisitService;

@Service
@Profile("springdatajpa")
public class VisitServiceImpl implements VisitService {

	private final VisitRepository visitRepository;

	public VisitServiceImpl(VisitRepository visitRepository) {
		super();
		this.visitRepository = visitRepository;
	}

	@Override
	public Visit findById(Long id) {
		return visitRepository.findById(id).orElse(null);
	}

	@Override
	public Set<Visit> findAll() {
		Set<Visit> visits = new HashSet<>();
		visitRepository.findAll().forEach(visits::add);
		return visits;
	}

	@Override
	public Visit save(Visit object) {
		return visitRepository.save(object);
	}

	@Override
	public void delete(Visit object) {
		visitRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		visitRepository.deleteById(id);
	}

}
