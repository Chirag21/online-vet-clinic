package com.onlinevet.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinevet.clinic.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {

}
