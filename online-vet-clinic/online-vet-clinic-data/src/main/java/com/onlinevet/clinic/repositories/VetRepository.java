package com.onlinevet.clinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.onlinevet.clinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {

}
