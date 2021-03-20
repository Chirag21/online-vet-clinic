package com.onlinevet.clinic.map;

import java.util.Set;

import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.services.CrudService;

public class OwnerServiceMapImpl extends AbstractMapService<Owner, Long> implements CrudService<Owner, Long> {

	public Owner findById(Long id) {
		return super.findById(id);
	}

	public Set<Owner> findAll() {
		return super.findAll();
	}

	public Owner save(Owner object) {
		return super.save(object.getId(), object);
	}

	public void delete(Owner object) {
		super.delete(object);
	}

	public void deleteById(Long id) {
		super.deleteById(id);
	}

}
