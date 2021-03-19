package com.onlinevet.clinic.model;

public class PetType extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2192902960816082884L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
