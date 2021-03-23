package com.onlinevet.clinic.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Vet;
import com.onlinevet.clinic.services.VetService;

@Service
public class VetServiceMapImpl extends AbstractMapService<Vet, Long> implements VetService {
	public Vet findById(Long id) {
		return super.findById(id);
	}

	public Set<Vet> findAll() {
		return super.findAll();
	}

	public Vet save(Vet object) {
		return super.save(object);
	}

	public void delete(Vet object) {
		super.delete(object);
	}

	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public Vet findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
