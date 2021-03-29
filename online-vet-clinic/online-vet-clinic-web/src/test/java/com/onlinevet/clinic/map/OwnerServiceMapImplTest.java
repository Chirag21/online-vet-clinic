package com.onlinevet.clinic.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.onlinevet.clinic.model.Owner;

class OwnerServiceMapImplTest {
	
	OwnerServiceMapImpl ownerMapServiceImpl;
	private final Long ownerId = 1L;

	@BeforeEach
	void setUp() throws Exception {
		ownerMapServiceImpl = new OwnerServiceMapImpl(new PetTypeServiceMapImlp(),new PetServiceMapImpl());
		ownerMapServiceImpl.save(Owner.builder().id(ownerId).firstName("John").lastName("Wick").build());
	}

	@Test
	void testFindByIdLong() {
		Owner owner = ownerMapServiceImpl.findById(ownerId);
		assertEquals(ownerId, owner.getId());
	}

	@Test
	void testFindAll() {
		Set<Owner> ownerSet = ownerMapServiceImpl.findAll();
		assertEquals(1, ownerSet.size());
	}

	@Test
	void testSaveOwner() {
		Long id = 2L;
		Owner owner = Owner.builder().id(id).build();
		Owner savedOwner = ownerMapServiceImpl.save(owner);
		assertEquals(id, savedOwner.getId());
	}
	
	@Test
	void saveWithNewId() {
		Owner owner = ownerMapServiceImpl.save(Owner.builder().build());
		assertNotNull(owner);
		assertNotNull(owner.getId());
	}

	@Test
	void testDeleteOwner() {
		ownerMapServiceImpl.delete(ownerMapServiceImpl.findById(ownerId));
		assertEquals(0, ownerMapServiceImpl.findAll().size());
	}

	@Test
	void testDeleteByIdLong() {
		ownerMapServiceImpl.deleteById(ownerId);
		assertEquals(0, ownerMapServiceImpl.findAll().size());
	}

	@Test
	void testFindByFirstName() {
		Owner john = ownerMapServiceImpl.findByFirstName("John");
		assertEquals("John", john.getFirstName());
	}

	@Test
	void testFindByLastName() {
		Owner wick = ownerMapServiceImpl.findByLastName("Wick");
		assertEquals("Wick", wick.getLastName());
	}

}
