package com.onlinevet.clinic.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vets")
public class Vet extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5215521992612167205L;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "vet_specialities", joinColumns = @JoinColumn(name = "vet_id"), inverseJoinColumns = @JoinColumn(name = "speciality_id"))
	private Set<Speciality> specialities = new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    
    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "start_practice_date")
    private Date startPracticeDate;
    
    private String description;
    
    private String picturePath;

    @OneToOne //(optional = false)
    @JoinColumn(name = "week_schedule_id", referencedColumnName = "id")
    private WeekSchedule weekSchedule;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "vet")
    private Set<Pet> pet;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "vet")
    private Set<Appointment> appointments;
}
