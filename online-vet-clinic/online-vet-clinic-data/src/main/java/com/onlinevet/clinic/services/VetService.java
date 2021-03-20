package com.onlinevet.clinic.services;

import com.onlinevet.clinic.model.Vet;

public interface VetService extends CrudService<Vet, Long> {
	Vet findByName(String name);
}
