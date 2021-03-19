package com.onlinevet.clinic.services;

import java.util.Set;

import com.onlinevet.clinic.model.Vet;

public interface VetService {
	Vet findById(Long id);

	Vet save(Vet vet);

	Set<Vet> findAll();
}
