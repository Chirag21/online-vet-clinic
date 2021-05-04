package com.onlinevet.clinic.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Log;
import com.onlinevet.clinic.repository.LogRepository;
import com.onlinevet.clinic.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	LogRepository logRepository;
	
	@Override
	public Log findById(Long id) {
		return logRepository.findById(id).orElse(Log.builder().build());
	}

	@Override
	public Set<Log> findAll() {
		Set<Log> logs = new HashSet<>();
		logRepository.findAll().forEach(logs::add);
		return logs;
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
		return logRepository.save(log);
	}

	@Override
	public Page<Log> getAll(Pageable pageable) {
		Page<Log> logs = logRepository.findAll(pageable);
		List<Log> list = new ArrayList<>();
		logs.forEach(list::add);
		return new PageImpl<>(list, pageable, logs.getTotalElements());
	}

}
