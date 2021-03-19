package com.onlinevet.clinic.model;

import java.io.Serializable;

public class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -959454419632272600L;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
