package com.onlinevet.clinic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.onlinevet.clinic.model.Log;

public interface LogService extends CrudService<Log, Long>{
	Log save(Log log);
	
	Page<Log> getAll(Pageable pageable);
}
