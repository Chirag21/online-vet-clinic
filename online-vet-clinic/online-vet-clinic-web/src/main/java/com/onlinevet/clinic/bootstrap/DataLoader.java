package com.onlinevet.clinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.onlinevet.clinic.map.OwnerServiceMapImpl;
import com.onlinevet.clinic.map.VetServiceMapImpl;
import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.model.Vet;
import com.onlinevet.clinic.services.OwnerSerivce;
import com.onlinevet.clinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerSerivce ownerService;
	private final VetService vetService;
	
	//no need for @Autowired after Spring 4.2 if there is only 1 constructor
	public DataLoader(OwnerSerivce ownerService,VetService vetService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
	}

	@Override
	public void run(String... args) throws Exception {
		Owner shahrukh = new Owner();
		shahrukh.setId(1L);
		shahrukh.setFirstName("Shahrukh");
		shahrukh.setLastName("Khan");
		ownerService.save(shahrukh);

		Owner salman = new Owner();
		salman.setId(1L);
		salman.setFirstName("Salman");
		salman.setLastName("Khan");
		ownerService.save(salman);

		System.out.println("Loaded owners .......");

		Vet aamir = new Vet();
		aamir.setFirstName("Aaamir");
		aamir.setLastName("Khan");
		vetService.save(aamir);

		Vet virat = new Vet();
		virat.setFirstName("Virat");
		virat.setLastName("Kohli");
		vetService.save(virat);

		System.out.println("Vets Loaded .......");
	}

}
