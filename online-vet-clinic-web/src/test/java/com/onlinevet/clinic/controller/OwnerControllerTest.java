package com.onlinevet.clinic.controller;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.onlinevet.clinic.controllers.OwnerController;
import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.services.OwnerService;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
	@Mock
	OwnerService ownerService;

	@InjectMocks
	OwnerController ownerConroller;

	Set<Owner> owners;
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		owners = new HashSet<>();
		owners.add(Owner.builder().id(1L).build());
		owners.add(Owner.builder().id(2L).build());
		mockMvc = MockMvcBuilders.standaloneSetup(ownerConroller).build();
	}

	@Test
	void testListOwners() {
		when(ownerService.findAll()).thenReturn(owners);

		try {
			mockMvc.perform(get("/owners"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/ownerList"))
				.andExpect(model().attribute("owners", Matchers.hasSize(2)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testFindowners() {
		try {
			mockMvc.perform(get("/owners/find"))
				.andExpect(status().isOk())
				.andExpect(view().name("NotImplementedYet"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		verifyNoInteractions(ownerService);
	}

}
