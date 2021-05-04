package com.onlinevet.clinic.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Speciality;
import com.onlinevet.clinic.service.SpecialityService;

@Service
@Profile({"default","map"})
public class SpecialityServiceMapImpl extends AbstractMapService<Speciality, Long> implements SpecialityService {

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
