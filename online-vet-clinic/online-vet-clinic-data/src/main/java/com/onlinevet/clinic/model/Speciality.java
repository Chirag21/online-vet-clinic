package com.onlinevet.clinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "specialities")
public class Speciality extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5353194709987645203L;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
