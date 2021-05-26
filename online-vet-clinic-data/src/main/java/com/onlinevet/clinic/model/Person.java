package com.onlinevet.clinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

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
	
    @Size(min = 5, message = "Username too short")
    @Column(name = "username")
    private String username;

    @Size(min = 5, message = "Password too short")
    @Column(name="password")
    private String password;
    
    @Size(min = 5, message = "Password too short")
    @Column(insertable=false, updatable=false)
    private String confirmPassword;


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
