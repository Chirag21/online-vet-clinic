package com.onlinevet.clinic.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.repository.OwnerRepository;
import com.onlinevet.clinic.repository.PetRepository;
import com.onlinevet.clinic.repository.PetTypeRepository;
import com.onlinevet.clinic.serviceimpl.OwnerServiceImpl;

@ExtendWith(MockitoExtension.class)
class OwnerServiceJpaTest {
	private static final String JOHN = "John";
	private static final String WICK = "Wick";
	Owner returnOwner;

	@Mock
	OwnerRepository ownerRepository;

	@Mock
	PetRepository petRepository;

	@Mock
	PetTypeRepository petTypeRepository;

	@InjectMocks
	OwnerServiceImpl ownerServiceJpa;

	@BeforeEach
	void setUp() throws Exception {
		returnOwner = Owner.builder().id(1L).firstName(JOHN).lastName(WICK).build();
	}

	@Test
	void testFindById() {
		when(ownerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(returnOwner));
		Owner owner = ownerServiceJpa.findById(1L);
		assertNotNull(owner);
	}

	@Test
	void testFindByIdNotFound() {
		when(ownerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		Owner owner = ownerServiceJpa.findById(1L);
		assertNull(owner);
	}

	@Test
	void testFindAll() {
		List<Owner> returnOwnersList = new ArrayList<>();
		returnOwnersList.add(Owner.builder().id(1L).build());
		returnOwnersList.add(Owner.builder().id(2L).build());
		when(ownerRepository.findAll()).thenReturn(returnOwnersList);

		Set<Owner> owners = ownerServiceJpa.findAll();
		assertNotNull(owners);
		assertEquals(2, owners.size());
	}

	@Test
	void testSave() {
		Owner ownerToSave = Owner.builder().id(1L).build();
		when(ownerRepository.save(ArgumentMatchers.any())).thenReturn(returnOwner);
		Owner savedOwner = ownerServiceJpa.save(ownerToSave);
		assertNotNull(savedOwner);
		verify(ownerRepository, times(1)).save(ArgumentMatchers.any()); // default is times(1). It's optional for 1.
	}

	@Test
	void testFindByFirstName() {
		when(ownerRepository.findByFirstName(ArgumentMatchers.any())).thenReturn(returnOwner);
		Owner owner = ownerServiceJpa.findByFirstName(JOHN);
		assertEquals(JOHN, owner.getFirstName());
		verify(ownerRepository, times(1)).findByFirstName(ArgumentMatchers.any()); // default is times(1). It's optional
																					// for 1.
	}

	@Test
	void testFindByLastName() {
		when(ownerRepository.findByLastName(ArgumentMatchers.any())).thenReturn(returnOwner);
		Owner owner = ownerServiceJpa.findByLastName(WICK);
		assertEquals(WICK, owner.getLastName());
		verify(ownerRepository, times(1)).findByLastName(ArgumentMatchers.any()); // default is times(1). It's optional
																					// for 1.
	}

	@Test
	void testDelete() {
		ownerServiceJpa.delete(returnOwner);
		verify(ownerRepository, times(1)).delete(ArgumentMatchers.any()); // default is times(1). It's optional for 1.
	}

	@Test
	void testDeleteById() {
		ownerServiceJpa.deleteById(1L);
		verify(ownerRepository, times(1)).deleteById(ArgumentMatchers.anyLong()); // default is times(1). It's optional
																					// for 1.
	}

}
