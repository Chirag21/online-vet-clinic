package com.onlinevet.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class OnlineVetClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineVetClinicApplication.class, args);
	}
}
