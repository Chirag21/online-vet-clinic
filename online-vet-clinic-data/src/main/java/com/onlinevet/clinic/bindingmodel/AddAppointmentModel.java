package com.onlinevet.clinic.bindingmodel;

import java.util.Date;

import javax.validation.constraints.Size;

import com.onlinevet.clinic.model.Pet;
import com.onlinevet.clinic.model.Vet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddAppointmentModel {
	   private Date date;

	    private long type;

	    @Size(max = 256, message = "Invalid description length")
	    private String description;

	    private Pet pet;

	    private Vet vet;

}
