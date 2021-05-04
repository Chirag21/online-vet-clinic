package com.onlinevet.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.onlinevet.clinic.model.Log;

public interface LogRepository extends JpaRepository<Log, Long> {

}
