package com.onlinevet.clinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "roles")
@ToString
@EqualsAndHashCode
public class Role extends BaseEntity {

	private static final long serialVersionUID = -3982694917787914936L;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "active")
	private char active;

	public Role(Long id, String name) {
		super(id);
		this.name = name;
	}
}
