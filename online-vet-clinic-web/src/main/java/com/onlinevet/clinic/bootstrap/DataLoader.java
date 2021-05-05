package com.onlinevet.clinic.bootstrap;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.model.Pet;
import com.onlinevet.clinic.model.PetType;
import com.onlinevet.clinic.model.Speciality;
import com.onlinevet.clinic.model.User;
import com.onlinevet.clinic.model.Vet;
import com.onlinevet.clinic.model.Visit;
import com.onlinevet.clinic.service.OwnerService;
import com.onlinevet.clinic.service.PetTypeService;
import com.onlinevet.clinic.service.SpecialityService;
import com.onlinevet.clinic.service.UserService;
import com.onlinevet.clinic.service.VetService;
import com.onlinevet.clinic.service.VisitService;

@Component
public class DataLoader implements CommandLineRunner {

	private OwnerService ownerService;
	private VetService vetService;
	private PetTypeService petTypeService;
	private SpecialityService specialityService;
	private VisitService visitService;
	private UserService userService;

	// no need for @Autowired after Spring 4.2 if there is only 1 constructor
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
			SpecialityService specialityService, VisitService visitService, UserService userService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialityService = specialityService;
		this.visitService = visitService;
		this.userService = userService;
	}

	@Override
	public void run(String... args) throws Exception {
		if (petTypeService.findAll().isEmpty())
			loadData();
	}

	private void loadData() {
		User user = new User();
		user.setId(1L);
		user.setEmail("admin1@gmail.com");
		user.setUsername("admin1");
		user.setPassword("$2a$10$H3vmHuhZg4SfU7tM4FI40ulLqdHGRo5H5HU3YBySZNwHWCjQ5kCRe");
		user.setRole("ROLE_ADMIN");
		userService.save(user);
		
		User userAquib = new User();
		userAquib.setId(2L);
		userAquib.setEmail("admin@gmail.com");
		userAquib.setUsername("admin");
		userAquib.setPassword("$2a$10$H3vmHuhZg4SfU7tM4FI40ulLqdHGRo5H5HU3YBySZNwHWCjQ5kCRe");
		userAquib.setRole("ROLE_ADMIN");
		userService.save(userAquib);
		
		User userManas = new User();
		userManas.setId(3L);
		userManas.setEmail("admin2@gmail.com");
		userManas.setUsername("admin2");
		userManas.setPassword("$2a$10$H3vmHuhZg4SfU7tM4FI40ulLqdHGRo5H5HU3YBySZNwHWCjQ5kCRe");
		userManas.setRole("ROLE_ADMIN");
		userService.save(userManas);
		
		User userNilesh = new User();
		userNilesh.setId(4L);
		userNilesh.setEmail("admin3@gmail.com");
		userNilesh.setUsername("admin3");
		userNilesh.setPassword("$2a$10$H3vmHuhZg4SfU7tM4FI40ulLqdHGRo5H5HU3YBySZNwHWCjQ5kCRe");
		userNilesh.setRole("ROLE_ADMIN");
		userService.save(userNilesh);
		
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
		aquib.setEmail("aquib@gmail.com");
		aquib.setUser(userAquib);
		ownerService.save(aquib);
		Pet aquibsPet = new Pet();
		aquibsPet.setPetType(cat);
		aquibsPet.setName("Bubble");
		aquibsPet.setOwner(aquib);
		aquibsPet.setBirthDate(new Date());
		aquib.getPets().add(aquibsPet);
		//ownerService.save(aquib);

		Owner manas = new Owner();
		manas.setId(2L);
		manas.setFirstName("Manas");
		manas.setLastName("Pawar");
		manas.setAddress("CBD Sector-5");
		manas.setCity("CBD Belapur");
		manas.setTelephone("8888888888");
		manas.setEmail("manas@gmail.com");
		manas.setUser(userManas);
		ownerService.save(manas);	// why saving this owner here works?
		Pet manassPet = new Pet();
		manassPet.setPetType(dog);
		manassPet.setName("Max");
		manassPet.setOwner(manas);
		manassPet.setBirthDate(new Date());
		manas.getPets().add(manassPet);
		//ownerService.save(manas);		why saving this owner here does not work?
				
		Owner nilesh = new Owner();
		nilesh.setId(3L);
		nilesh.setFirstName("Nilesh");
		nilesh.setLastName("Pawar");
		nilesh.setAddress("CBD Sector-4");
		nilesh.setCity("CBD Belapur");
		nilesh.setTelephone("7777777777");
		nilesh.setEmail("nilesh@gmail.com");
		nilesh.setUser(userNilesh);
		Pet nileshsPet = new Pet();
		nileshsPet.setPetType(dog);
		nileshsPet.setName("Bruno");
		nileshsPet.setOwner(nilesh);
		nileshsPet.setBirthDate(new Date());
		nilesh.getPets().add(nileshsPet);
		ownerService.save(nilesh);

		Visit catVisit = new Visit();
		catVisit.setPet(manassPet);
		catVisit.setDate(new Date());
		catVisit.setDescription("Cat Visit Done");
		visitService.save(catVisit); // pet id is null. need a look into

		System.out.println("Loaded owners .......");

		User userSagar = new User();
		userSagar.setId(5L);
		userSagar.setEmail("admin4@gmail.com");
		userSagar.setUsername("admin4");
		userSagar.setPassword("$2a$10$H3vmHuhZg4SfU7tM4FI40ulLqdHGRo5H5HU3YBySZNwHWCjQ5kCRe");
		userSagar.setRole("ROLE_ADMIN");
		userService.save(userSagar);
		
		Vet sagar = new Vet();
		sagar.setId(1L);
		sagar.setFirstName("Sagar");
		sagar.setLastName("Singh");
		sagar.getSpecialities().add(dentistry);
		sagar.getSpecialities().add(surgery);
		sagar.setUser(userSagar);
		vetService.save(sagar);

		
		User userGautam = new User();
		userGautam.setId(6L);
		userGautam.setEmail("admin5@gmail.com");
		userGautam.setUsername("admin5");
		userGautam.setPassword("$2a$10$H3vmHuhZg4SfU7tM4FI40ulLqdHGRo5H5HU3YBySZNwHWCjQ5kCRe");
		userGautam.setRole("ROLE_ADMIN");
		userService.save(userGautam);
		
		Vet gautam = new Vet();
		gautam.setId(2L);
		gautam.setFirstName("Gautam");
		gautam.setLastName("Singh");
		gautam.getSpecialities().add(radiology);
		gautam.setUser(userGautam);
		vetService.save(gautam);
		
		
		User userKunal = new User();
		userKunal.setId(7L);
		userKunal.setEmail("admin6@gmail.com");
		userKunal.setUsername("admin6");
		userKunal.setPassword("$2a$10$H3vmHuhZg4SfU7tM4FI40ulLqdHGRo5H5HU3YBySZNwHWCjQ5kCRe");
		userKunal.setRole("ROLE_ADMIN");
		userService.save(userKunal);
		
		Vet kunal = new Vet();
		kunal.setId(3L);
		kunal.setFirstName("Kunal");
		kunal.setLastName("Choramale");
		kunal.getSpecialities().add(radiology);
		kunal.setUser(userKunal);
		vetService.save(kunal);

		System.out.println("Vets Loaded .......");
	}

}
