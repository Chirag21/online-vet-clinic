package com.onlinevet.clinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7297421137508112975L;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	

    //@NotBlank(message = "Invalid email address")
    //@Email(message = "Invalid email address")
    @Column(name = "email")
    @Nullable
    private String email;
	
	public Person(Long id,String firstName,String lastName, String email) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

}
