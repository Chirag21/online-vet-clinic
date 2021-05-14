package com.onlinevet.clinic.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {
	private static final long serialVersionUID = -4401687880835935591L;
	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private PetType petType;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;

	@NotNull(message = "Invalid birth date")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "birth_date")
	private Date birthDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
	private Set<Visit> visits = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vet_id")
    private Vet vet;
	
	@Builder
	public Pet(Long id, String name, PetType petType, Owner owner, Date birthDate, Set<Visit> visits) {
		super(id);
		this.name = name;
		this.petType = petType;
		this.owner = owner;
		this.birthDate = birthDate;
		this.visits = visits;
	}
}
