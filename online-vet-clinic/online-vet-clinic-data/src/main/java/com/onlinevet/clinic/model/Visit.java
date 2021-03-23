package com.onlinevet.clinic.model;

import java.time.LocalDate;

public class Visit extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6363360173075367566L;
	private LocalDate date;
	private String descriprtion;
	private Pet pet;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescriprtion() {
		return descriprtion;
	}

	public void setDescriprtion(String descriprtion) {
		this.descriprtion = descriprtion;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
}
