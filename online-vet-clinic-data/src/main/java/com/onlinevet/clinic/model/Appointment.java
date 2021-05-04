package com.onlinevet.clinic.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment extends BaseEntity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vet;

    @Column(name = "description")
    private String description;

	public Appointment(Long id, Date date, Pet pet, Vet vet, String description) {
		super(id);
		this.date = date;
		this.pet = pet;
		this.vet = vet;
		this.description = description;
	}
}
