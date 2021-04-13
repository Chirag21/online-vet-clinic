package com.onlinevet.clinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2192902960816082884L;

	@Column(name = "name")
	private String name;
	
	@Builder
	public PetType(Long id, String name) {
		super(id);
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
