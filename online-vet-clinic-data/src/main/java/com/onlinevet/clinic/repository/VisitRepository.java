package com.onlinevet.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinevet.clinic.model.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {

}
