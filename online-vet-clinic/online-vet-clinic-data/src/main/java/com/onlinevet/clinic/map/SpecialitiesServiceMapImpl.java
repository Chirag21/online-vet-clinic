package com.onlinevet.clinic.map;

import java.util.Set;

import com.onlinevet.clinic.model.Speciality;
import com.onlinevet.clinic.services.SpecialitiesService;

public class SpecialitiesServiceMapImpl extends AbstractMapService<Speciality, Long> implements SpecialitiesService {

	@Override
	public Speciality findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Set<Speciality> findAll() {
		return super.findAll();
	}

	@Override
	public Speciality save(Speciality object) {
		return super.save(object);
	}

	@Override
	public void delete(Speciality object) {
		super.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

}
