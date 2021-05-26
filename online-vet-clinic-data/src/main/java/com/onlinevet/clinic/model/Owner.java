package com.onlinevet.clinic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.lang.Nullable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person implements Serializable{

    private static final long serialVersionUID = -7746215795670026362L;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "telephone")
	private String telephone;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
	private Set<Pet> pets = new HashSet<>();
	
    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Nullable
    private User user;
    
    @Column(name = "is_profile_complete")
    private Character isProfileComplete;
    
    private String additionalRole;

    @Builder
	public Owner(Long id, String firstName, String lastName, String email , String address, String city, String telephone,
			Set<Pet> pets) {
		super(id, firstName, lastName,email);
		this.address = address;
		this.city = city;
		this.telephone = telephone;
		if(Objects.nonNull(pets))
			this.pets = pets;
	}
    
	protected Set<Pet> getPetsInternal() {
		if (this.pets == null) {
			this.pets = new HashSet<>();
		}
		return this.pets;
	}

	protected void setPetsInternal(Set<Pet> pets) {
		this.pets = pets;
	}

	public List<Pet> getPets() {
		List<Pet> sortedPets = new ArrayList<>(getPetsInternal());
		PropertyComparator.sort(sortedPets, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(sortedPets);
	}

	public void addPet(Pet pet) {
		if (pet.isNew()) {
			getPetsInternal().add(pet);
		}
		pet.setOwner(this);
	}
    
	/**
	 * Return the Pet with the given name, or null if none found for this Owner.
	 * @param name to test
	 * @return true if pet name is already in use
	 */
	public Pet getPet(String name) {
		return getPet(name, false);
	}

	/**
	 * Return the Pet with the given name, or null if none found for this Owner.
	 * @param name to test
	 * @return true if pet name is already in use
	 */
	public Pet getPet(String name, boolean ignoreNew) {
		name = name.toLowerCase();
		for (Pet pet : getPetsInternal()) {
			if (!ignoreNew || !pet.isNew()) {
				String compName = pet.getName();
				compName = compName.toLowerCase();
				if (compName.equals(name)) {
					return pet;
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Owner [address=" + address + ", city=" + city + ", telephone=" + telephone + ", pets=" + pets
				+ ", user=" + user + ", isProfileComplete=" + isProfileComplete + ", additionalRole=" + additionalRole
				+ "]";
	}
}
