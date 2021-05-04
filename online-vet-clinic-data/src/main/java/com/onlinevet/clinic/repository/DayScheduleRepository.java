package com.onlinevet.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinevet.clinic.model.DaySchedule;

public interface DayScheduleRepository extends JpaRepository<DaySchedule, Long> {

}
