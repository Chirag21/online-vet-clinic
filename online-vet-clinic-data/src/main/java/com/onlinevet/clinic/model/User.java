package com.onlinevet.clinic.model;

import java.io.Serializable;
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
import javax.validation.constraints.Email;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "user_name"))
@ToString
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity implements UserDetails, Serializable{

	private static final long serialVersionUID = 2732492892459817441L;

	@Column(name = "user_name")
	private String username;

	@Column(name = "email")
	@Email
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(insertable = false, updatable = false)
	private String oldPassword;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
				, inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> authorities;

	@Column(name = "role", nullable = false)
	private String role;

	@Column(name = "active")
	private char active;

	@Column(name = "reset_password_token")
	private String resetPasswordToken;
	
	@Column(name = "is_account_non_expired")
	@Default
    private boolean isAccountNonExpired = true;

	@Column(name = "is_account_non_locked")
    @Default
	private boolean isAccountNonLocked = true;

	@Column(name = "is_credentails_non_expired")
    @Default
	private boolean isCredentialsNonExpired = true;

	@Column(name = "is_enabled")
    @Default
	private boolean isEnabled = true;
	
	@Override
	public Set<Role> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

    public void addRole(Role role) {
        this.getAuthorities().add(role);
    }
}
