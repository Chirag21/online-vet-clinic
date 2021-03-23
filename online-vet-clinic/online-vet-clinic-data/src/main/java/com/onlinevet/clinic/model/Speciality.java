package com.onlinevet.clinic.model;

import java.util.Set;

public class Speciality extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5353194709987645203L;
	private Set<Speciality> specialites;

	public Set<Speciality> getSpecialites() {
		return specialites;
	}

	public void setSpecialites(Set<Speciality> specialites) {
		this.specialites = specialites;
	}
}
