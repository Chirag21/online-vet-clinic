package com.onlinevet.clinic.map;

import java.util.Set;

import com.onlinevet.clinic.model.Vet;
import com.onlinevet.clinic.services.CrudService;

public class VetServiceMapImpl extends AbstractMapService<Vet, Long> implements CrudService<Vet, Long> {
	public Vet findById(Long id) {
		return super.findById(id);
	}

	public Set<Vet> findAll() {
		return super.findAll();
	}

	public Vet save(Vet object) {
		return super.save(object.getId(), object);
	}

	public void delete(Vet object) {
		super.delete(object);
	}

	public void deleteById(Long id) {
		super.deleteById(id);
	}
}
