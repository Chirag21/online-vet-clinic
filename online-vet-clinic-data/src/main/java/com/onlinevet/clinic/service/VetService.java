package com.onlinevet.clinic.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.onlinevet.clinic.model.Vet;

public interface VetService extends CrudService<Vet, Long> {
	Vet findByFirstName(String firstName);
	
	Vet findByLastName(String lastName);
	
    Vet findByUserId(Long id);
	
	List<Vet> findAllByOrderByFirstNameAscLastName();
	
	void create(Vet vet);
    
    void savePicture(Vet vet);

    void savePicture(Vet vet, String picturePath);
    
    List<Vet> findAllSelect();

    Page<Vet> findAll(Pageable pageable);
    
}
