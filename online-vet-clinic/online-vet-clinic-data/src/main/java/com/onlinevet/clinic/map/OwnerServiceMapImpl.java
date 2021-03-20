package com.onlinevet.clinic.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.services.OwnerSerivce;

@Service
public class OwnerServiceMapImpl extends AbstractMapService<Owner, Long> implements OwnerSerivce {

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

	@Override
	public Owner findByFirstName(String firstName) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public Owner findByLastName(String lastName) {
		// TODO Auto-generated method stub

		return null;
	}
}
