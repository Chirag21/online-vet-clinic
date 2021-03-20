package com.onlinevet.clinic.services;

import com.onlinevet.clinic.model.Owner;

public interface OwnerSerivce extends CrudService<Owner, Long> {
	Owner findByFirstName(String firstName);

	Owner findByLastName(String lastName);

}
