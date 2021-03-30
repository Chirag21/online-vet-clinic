package com.onlinevet.clinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.model.Pet;
import com.onlinevet.clinic.model.PetType;
import com.onlinevet.clinic.model.Speciality;
import com.onlinevet.clinic.model.Vet;
import com.onlinevet.clinic.model.Visit;
import com.onlinevet.clinic.services.OwnerService;
import com.onlinevet.clinic.services.PetTypeService;
import com.onlinevet.clinic.services.SpecialityService;
import com.onlinevet.clinic.services.VetService;
import com.onlinevet.clinic.services.VisitService;

@Component
public class DataLoader implements CommandLineRunner {

	private OwnerService ownerService;
	private VetService vetService;
	private PetTypeService petTypeService;
	private SpecialityService specialityService;
	private VisitService visitService;

	// no need for @Autowired after Spring 4.2 if there is only 1 constructor
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
			SpecialityService specialityService, VisitService visitService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialityService = specialityService;
		this.visitService = visitService;
	}

	@Override
	public void run(String... args) throws Exception {
		if (petTypeService.findAll().isEmpty())
			loadData();
	}

	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		petTypeService.save(dog);

		PetType cat = new PetType();
		cat.setName("Cat");
		petTypeService.save(cat);

		Speciality radiology = new Speciality();
		radiology.setDescription("Radiology");
		specialityService.save(radiology);

		Speciality surgery = new Speciality();
		surgery.setDescription("Surgey");
		specialityService.save(surgery);

		Speciality dentistry = new Speciality();
		dentistry.setDescription("Dentistry");
		specialityService.save(dentistry);

		Owner aquib = new Owner();
		aquib.setId(1L);
		aquib.setFirstName("Aquib");
		aquib.setLastName("Haider");
		aquib.setAddress("Taloja Sector-35");
		aquib.setCity("Taloja");
		aquib.setTelephone("9999999999");
		Pet aquibsPet = new Pet();
		aquibsPet.setPetType(cat);
		aquibsPet.setName("Bubble");
		aquibsPet.setOwner(aquib);
		aquibsPet.setBirthDate(LocalDate.now());
		aquib.getPets().add(aquibsPet);
		ownerService.save(aquib);

		Owner manas = new Owner();
		manas.setId(2L);
		manas.setFirstName("Manas");
		manas.setLastName("Pawar");
		manas.setAddress("CBD Sector-5");
		manas.setCity("CBD Belapur");
		manas.setTelephone("8888888888");
		Pet manassPet = new Pet();
		manassPet.setPetType(dog);
		manassPet.setName("Max");
		manassPet.setOwner(aquib);
		manassPet.setBirthDate(LocalDate.now());
		manas.getPets().add(manassPet);
		ownerService.save(manas);

		Visit catVisit = new Visit();
		catVisit.setPet(manassPet);
		catVisit.setDate(LocalDate.now());
		catVisit.setDescription("Cat Visit Done");
		visitService.save(catVisit); // pet id is null. need a look into

		System.out.println("Loaded owners .......");

		Vet sagar = new Vet();
		sagar.setId(1L);
		sagar.setFirstName("Sagar");
		sagar.setLastName("Singh");
		sagar.getSpecialities().add(dentistry);
		sagar.getSpecialities().add(surgery);
		vetService.save(sagar);

		Vet gautam = new Vet();
		gautam.setId(2L);
		gautam.setFirstName("Gautam");
		gautam.setLastName("Singh");
		gautam.getSpecialities().add(radiology);
		vetService.save(gautam);

		System.out.println("Vets Loaded .......");
	}

}
