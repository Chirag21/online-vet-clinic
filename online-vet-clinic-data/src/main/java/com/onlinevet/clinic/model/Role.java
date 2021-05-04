package com.onlinevet.clinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Table(name = "roles")
public class Role extends BaseEntity{
	
	private static final long serialVersionUID = -3982694917787914936L;

	@Column(name = "authority")
    private String authority;
	
    @Column(name = "active")
    private char active;
    
    public Role(Long id, String authority) {
    	super(id);
    	this.authority = authority;
    }

	public Role(String authority) {
		this.authority = authority;
	}
}
