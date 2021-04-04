package com.onlinevet.clinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2192902960816082884L;

	@Column(name = "name")
	private String name;

	@Override
	public String toString() {
		return name;
	}
}
