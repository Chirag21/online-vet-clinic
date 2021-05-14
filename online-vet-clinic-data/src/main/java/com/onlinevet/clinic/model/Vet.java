package com.onlinevet.clinic.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vets")
public class Vet extends Person {

	private static final long serialVersionUID = -5215521992612167205L;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "vet_specialities", joinColumns = @JoinColumn(name = "vet_id"), inverseJoinColumns = @JoinColumn(name = "speciality_id"))
	@Builder.Default
	private Set<Speciality> specialities = new HashSet<>();

    //@NotNull(message = "Invalid gender.")
    //@Pattern(regexp = "^(MALE|FEMALE)$", message = "Invalid gender.")
    @Enumerated(value = EnumType.STRING)
    @Nullable
    private Gender gender;
    
    @Column(name = "registration_number")
    private String registrationNumber;
    
    @Column(name = "telephone")
    private String telephone;

    @Nullable
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    @NotNull(message = "Invalid start practice date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
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
    private Set<Pet> pets;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "vet")
    private Set<Appointment> appointments;
    
    private String additionalRole;
}
