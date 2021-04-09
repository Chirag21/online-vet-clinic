package com.onlinevet.clinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "user_name"))
@ToString
@EqualsAndHashCode
public class User extends Person {

	private static final long serialVersionUID = 2732492892459817441L;

	@Column(name ="user_name")
	private String userName;

    @Column(name= "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(
            name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(
            name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
    
    @Column(name = "active")
    private char active;

    @Builder
    public User(Long id, String firstName, String lastName, String userName, String password, Set <Role> roles , char active) {
        super(id,firstName,lastName);
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.active = active;
    }

}
