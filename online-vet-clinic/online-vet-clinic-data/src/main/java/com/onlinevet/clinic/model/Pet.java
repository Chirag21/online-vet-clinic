package com.onlinevet.clinic.model;

import java.time.LocalDate;

public class Pet extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4401687880835935591L;
	PetType petType;
	private Owner owner;
	private LocalDate localDate;

	public PetType getPetType() {
		return petType;
	}

	public void setPetType(PetType petType) {
		this.petType = petType;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

}
