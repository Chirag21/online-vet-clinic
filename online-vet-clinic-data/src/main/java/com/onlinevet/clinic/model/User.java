package com.onlinevet.clinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "users")
public class User extends Person{

	private static final long serialVersionUID = 6354007992837525445L;

	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "active")
	private char active;
	
	public User(Long id,String firstName,String lastName,String userName,String password,char active) {
		super(id,firstName,lastName);
		this.userName = userName;
		this.password = password;
		this.active = active;
	}
}
