package com.onlinevet.clinic.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.services.OwnerService;
import com.onlinevet.clinic.services.PetService;
import com.onlinevet.clinic.services.PetTypeService;

//@Service
//@Profile({"default","map"})
public class OwnerServiceMapImpl extends AbstractMapService<Owner, Long> implements OwnerService {

	private final PetTypeService petTypeService;
	private final PetService petService;

	public OwnerServiceMapImpl(PetTypeService petTypeService,PetService petService) {
		super();
		this.petTypeService = petTypeService;
		this.petService = petService;
	}

	public Owner findById(Long id) {
		return super.findById(id);
	}

	public Set<Owner> findAll() {
		return super.findAll();
	}

	public Owner save(Owner object) {
		System.out.println("MAP MAP MAP MAP MAP MAP MAP MAP MAP");
		System.out.println("MAP MAP MAP MAP MAP MAP MAP MAP MAP");
		System.out.println("MAP MAP MAP MAP MAP MAP MAP MAP MAP");
		System.out.println("MAP MAP MAP MAP MAP MAP MAP MAP MAP");
		System.out.println("MAP MAP MAP MAP MAP MAP MAP MAP MAP");
		System.out.println("MAP MAP MAP MAP MAP MAP MAP MAP MAP");
		System.out.println("MAP MAP MAP MAP MAP MAP MAP MAP MAP");
		if (object.getPets() != null) {
			object.getPets().forEach(pet -> {
				if (pet.getPetType() != null) {
					if (pet.getPetType().getId() == null) {
						pet.setPetType(petTypeService.save(pet.getPetType()));
					}
				} else {
					throw new RuntimeException("Pet Type is required");
				}
				if(pet.getId()==null) {
					pet.setId(petService.save(pet).getId());
				}
			});
			return super.save(object);
		} else
			return null;
	}

	public void delete(Owner object) {
		super.delete(object);
	}

	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public Owner findByFirstName(String firstName) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public Owner findByLastName(String lastName) {
		// TODO Auto-generated method stub

		return null;
	}
}
