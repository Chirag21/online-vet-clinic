package com.onlinevet.clinic.map;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.Speciality;
import com.onlinevet.clinic.model.Vet;
import com.onlinevet.clinic.service.SpecialityService;
import com.onlinevet.clinic.service.VetService;

@Service
@Profile({"map"})
public class VetServiceMapImpl extends AbstractMapService<Vet, Long> implements VetService {
	private SpecialityService specialityService;

	public VetServiceMapImpl(SpecialityService specialityService) {
		super();
		this.specialityService = specialityService;
	}

	public Vet findById(Long id) {
		return super.findById(id);
	}

	public Set<Vet> findAll() {
		return super.findAll();
	}

	public Vet save(Vet object) {
		if (Objects.nonNull(object)) {
			if (!object.getSpecialities().isEmpty()) {
				object.getSpecialities().forEach(speciality -> {
					if (Objects.isNull(speciality.getId())) {
						Speciality savedSpeciality = specialityService.save(speciality);
						speciality.setId(savedSpeciality.getId());
					}
				});
			}
		}
		return super.save(object);
	}

	public void delete(Vet object) {
		super.delete(object);
	}

	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public Vet findByFirstName(String firstName) {
		return null;
	}

	@Override
	public Vet findByLastName(String lastName) {
		return null;
	}

	@Override
	public void create(Vet vet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePicture(Vet vet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vet findByUserId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vet> findAllSelect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Vet> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vet> findAllByOrderByFirstNameAscLastName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePicture(Vet vet, String picturePath) {
		// TODO Auto-generated method stub
		
	}
}
