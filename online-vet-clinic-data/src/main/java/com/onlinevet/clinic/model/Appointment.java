package com.onlinevet.clinic.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import lombok.Builder;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vet_id")
    private Vet vet;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_id")
    private Pet pet;
    
	@NotNull(message = "Invalid birth date")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "date")
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @NotBlank
    @Size(max = 255 , message = "Invalid description length")
    @Column(name = "description")
    private String description;
    
    @Column(name = "mobile_no")
    private String mobileNo;
    
    //@NotBlank
    @Nullable
    @Column(name = "status")
    private String status;

    @Builder
	public Appointment(Long id, Date date, Pet pet, Vet vet, String description) {
		super(id);
		this.date = date;
		this.pet = pet;
		this.vet = vet;
		this.description = description;
	}
}
