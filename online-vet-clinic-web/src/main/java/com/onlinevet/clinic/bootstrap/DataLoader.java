package com.onlinevet.clinic.bootstrap;

import java.util.Calendar;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.onlinevet.clinic.model.Appointment;
import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.model.Pet;
import com.onlinevet.clinic.model.PetType;
import com.onlinevet.clinic.model.Speciality;
import com.onlinevet.clinic.model.User;
import com.onlinevet.clinic.model.Vet;
import com.onlinevet.clinic.model.Visit;
import com.onlinevet.clinic.service.AppointmentService;
import com.onlinevet.clinic.service.OwnerService;
import com.onlinevet.clinic.service.PetTypeService;
import com.onlinevet.clinic.service.SpecialityService;
import com.onlinevet.clinic.service.UserService;
import com.onlinevet.clinic.service.VetService;
import com.onlinevet.clinic.service.VisitService;

@Component
@Profile(value = "test")
public class DataLoader implements CommandLineRunner {

	private static final String ROLE_VET = "ROLE_VET";
	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	private static final String ROLE_OWNER = "ROLE_OWNER";
	private OwnerService ownerService;
	private VetService vetService;
	private PetTypeService petTypeService;
	private SpecialityService specialityService;
	private VisitService visitService;
	private UserService userService;
	private AppointmentService appointmentService;
	private static Calendar cal = Calendar.getInstance();

	// no need for @Autowired after Spring 4.2 if there is only 1 constructor
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
			SpecialityService specialityService, VisitService visitService, UserService userService,
			AppointmentService appointmentService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialityService = specialityService;
		this.visitService = visitService;
		this.userService = userService;
		this.appointmentService = appointmentService;
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
		user.setUsername("admin");
		user.setPassword("$2a$10$H3vmHuhZg4SfU7tM4FI40ulLqdHGRo5H5HU3YBySZNwHWCjQ5kCRe");
		user.setRole(ROLE_ADMIN);
		user.setActive('Y');
		userService.save(user);
		
		User userAquib = new User();
		userAquib.setId(2L);
		userAquib.setEmail("admin@gmail.com");
		userAquib.setUsername("owner1");
		userAquib.setPassword("$2a$10$H3vmHuhZg4SfU7tM4FI40ulLqdHGRo5H5HU3YBySZNwHWCjQ5kCRe");
		userAquib.setRole(ROLE_OWNER);
		userAquib.setActive('Y');
		userService.save(userAquib);
		
		User userManas = new User();
		userManas.setId(3L);
		userManas.setEmail("admin2@gmail.com");
		userManas.setUsername("owner2");
		userManas.setPassword("$2a$10$H3vmHuhZg4SfU7tM4FI40ulLqdHGRo5H5HU3YBySZNwHWCjQ5kCRe");
		userManas.setRole(ROLE_OWNER);
		userManas.setActive('Y');
		userService.save(userManas);
		
		User userNilesh = new User();
		userNilesh.setId(4L);
		userNilesh.setEmail("admin3@gmail.com");
		userNilesh.setUsername("owner3");
		userNilesh.setPassword("$2a$10$H3vmHuhZg4SfU7tM4FI40ulLqdHGRo5H5HU3YBySZNwHWCjQ5kCRe");
		userNilesh.setRole(ROLE_OWNER);
		userNilesh.setActive('Y');
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
		aquibsPet.setName("Aquib's Pet");
		aquibsPet.setOwner(aquib);
		aquibsPet.setBirthDate(new Date());
		//aquib.getPets().add(aquibsPet);
		//aquib.addPet(aquibsPet);
		//ownerService.save(aquib);
		
		Pet aquibsPetGigi = new Pet();
		aquibsPetGigi.setPetType(dog);
		aquibsPetGigi.setName("Aquib's Pet Gigi");
		aquibsPetGigi.setOwner(aquib);
		aquibsPetGigi.setBirthDate(new Date());
		//aquib.getPets().add(aquibsPet);
		//aquib.addPet(aquibsPetGigi);

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
		manassPet.setName("Manas's Pet");
		manassPet.setOwner(manas);
		manassPet.setBirthDate(new Date());
		//manas.getPets().add(manassPet);
		//manas.addPet(manassPet);
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
		nileshsPet.setName("Nilesh's Pet");
		nileshsPet.setOwner(nilesh);
		nileshsPet.setBirthDate(new Date());
		//nilesh.getPets().add(nileshsPet);
		//nilesh.addPet(nileshsPet);
		ownerService.save(nilesh);

		Visit catVisit = new Visit();
		catVisit.setPetId(manassPet.getId());
		catVisit.setDate(new Date());
		catVisit.setDescription("Cat Visit Done");
		visitService.save(catVisit);

		System.out.println("Loaded owners .......");

		User userSagar = new User();
		userSagar.setId(5L);
		userSagar.setEmail("admin4@gmail.com");
		userSagar.setUsername("vet1");
		userSagar.setPassword("$2a$10$H3vmHuhZg4SfU7tM4FI40ulLqdHGRo5H5HU3YBySZNwHWCjQ5kCRe");
		userSagar.setRole(ROLE_VET);
		userSagar.setActive('Y');
		userService.save(userSagar);
		
		Vet sagar = new Vet();
		sagar.setId(1L);
		sagar.setFirstName("Sagar");
		sagar.setLastName("Singh");
		sagar.getSpecialities().add(dentistry);
		sagar.getSpecialities().add(surgery);
		cal.set(2010, 11, 18);
		sagar.setTelephone("7777777777");
		sagar.setStartPracticeDate(cal.getTime());
		sagar.setRegistrationNumber("ABCD12345");
		sagar.setUser(userSagar);
		vetService.save(sagar);

		
		User userGautam = new User();
		userGautam.setId(6L);
		userGautam.setEmail("admin5@gmail.com");
		userGautam.setUsername("vet2");
		userGautam.setPassword("$2a$10$H3vmHuhZg4SfU7tM4FI40ulLqdHGRo5H5HU3YBySZNwHWCjQ5kCRe");
		userGautam.setRole(ROLE_VET);
		userGautam.setActive('Y');
		userService.save(userGautam);
		
		Vet gautam = new Vet();
		gautam.setId(2L);
		gautam.setFirstName("Gautam");
		gautam.setLastName("Singh");
		gautam.getSpecialities().add(radiology);
		cal.set(2000, 03, 13);
		gautam.setTelephone("8888888888");
		gautam.setStartPracticeDate(cal.getTime());
		gautam.setUser(userGautam);
		vetService.save(gautam);
		
		
		User userKunal = new User();
		userKunal.setId(7L);
		userKunal.setEmail("admin6@gmail.com");
		userKunal.setUsername("vet3");
		userKunal.setPassword("$2a$10$H3vmHuhZg4SfU7tM4FI40ulLqdHGRo5H5HU3YBySZNwHWCjQ5kCRe");
		userKunal.setRole(ROLE_VET);
		userKunal.setActive('Y');
		userService.save(userKunal);
		
		Vet kunal = new Vet();
		kunal.setId(3L);
		kunal.setFirstName("Kunal");
		kunal.setLastName("Choramale");
		kunal.getSpecialities().add(radiology);
		cal.set(2018, 05, 20);
		kunal.setTelephone("9999999999");
		kunal.setStartPracticeDate(cal.getTime());
		kunal.setUser(userKunal);
		vetService.save(kunal);

		System.out.println("Vets Loaded .......");
		
		Appointment aquibsAppointment = new Appointment();
		cal.set(2021, 01, 18);
		aquibsAppointment.setDate(cal.getTime());
		aquibsAppointment.setId(1L);
		aquibsAppointment.setOwner(aquib);
		aquibsAppointment.setPet(aquibsPet);
		aquibsAppointment.setVet(sagar);
		aquibsAppointment.setDescription("Aquib's Pet Visit done");
		aquibsAppointment.setStatus("C");
		appointmentService.save(aquibsAppointment);
		
		Appointment gigisAppointment = new Appointment();
		cal.set(2021, 01, 18);
		gigisAppointment.setDate(cal.getTime());
		gigisAppointment.setId(4L);
		gigisAppointment.setOwner(aquib);
		gigisAppointment.setPet(aquibsPetGigi);
		gigisAppointment.setVet(gautam);
		gigisAppointment.setDescription("Aquib's Pet Gigi's Visit done");
		gigisAppointment.setStatus("O");
		appointmentService.save(gigisAppointment);
		
		Appointment manassAppointment = new Appointment();
		cal.set(2021, 02, 07);
		manassAppointment.setDate(cal.getTime());
		manassAppointment.setId(2L);
		manassAppointment.setOwner(manas);
		manassAppointment.setPet(manassPet);
		manassAppointment.setVet(gautam);
		manassAppointment.setDescription("Manas's Pet Visit done");
		manassAppointment.setStatus("C");
		appointmentService.save(manassAppointment);
		
		Appointment nileshsAppointment = new Appointment();
		cal.set(2021, 06, 15);
		nileshsAppointment.setDate(cal.getTime());
		nileshsAppointment.setId(3L);
		nileshsAppointment.setOwner(nilesh);
		nileshsAppointment.setPet(nileshsPet);
		nileshsAppointment.setVet(kunal);
		nileshsAppointment.setDescription("Nilesh's Pet Visit done");
		nileshsAppointment.setStatus("O");
		appointmentService.save(nileshsAppointment);
		
		System.out.println("Loaded appointments .......");
	}
}
