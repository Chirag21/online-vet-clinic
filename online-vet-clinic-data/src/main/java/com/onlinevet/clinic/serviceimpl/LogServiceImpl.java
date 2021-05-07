package com.onlinevet.clinic.serviceimpl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Log;
import com.onlinevet.clinic.repository.LogRepository;
import com.onlinevet.clinic.service.LogService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class LogServiceImpl implements LogService {

	@Autowired
	private LogRepository logRepository;
	
	@Override
	public Log findById(Long id) {
		return logRepository.findById(id).orElse(Log.builder().build());
	}

	@Override
	public Set<Log> findAll() {
		return logRepository.findAll().stream().collect(Collectors.toSet());
	}

	@Override
	public void delete(Log log) {
		logRepository.delete(log);
	}

	@Override
	public void deleteById(Long id) {
		logRepository.deleteById(id);
	}

	@Override
	public Log save(Log log) {
		return logRepository.saveAndFlush(log);
	}

	@Override
	public Page<Log> getAll(Pageable pageable) {
		Page<Log> logs = logRepository.findAll(pageable);
		return new PageImpl<>(logs.toList(), pageable, logs.getTotalElements());
	}

}
