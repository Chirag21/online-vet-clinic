package com.onlinevet.clinic.model;

public class Person extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7297421137508112975L;
	private String firstName;
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

}
