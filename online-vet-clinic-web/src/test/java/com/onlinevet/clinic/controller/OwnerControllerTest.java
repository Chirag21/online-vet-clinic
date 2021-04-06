package com.onlinevet.clinic.controller;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.Matchers;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.onlinevet.clinic.controllers.OwnerController;
import com.onlinevet.clinic.model.Owner;
import com.onlinevet.clinic.services.OwnerService;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
	@Mock
	OwnerService ownerService;

	@InjectMocks
	OwnerController ownerController;

	Set<Owner> owners;
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		owners = new HashSet<>();
		owners.add(Owner.builder().id(1L).firstName("Ajit").lastName("Katkar").build());
		owners.add(Owner.builder().id(2L).firstName("Nilesh").lastName("Pawar").build());
		mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
	}

	/*
	 * @Test void testListOwners() {
	 * when(ownerService.findAll()).thenReturn(owners);
	 * 
	 * try {
	 * mockMvc.perform(get("/owners")).andExpect(status().isOk()).andExpect(view().
	 * name("owners/ownerList")) .andExpect(model().attribute("owners",
	 * Matchers.hasSize(2))); } catch (Exception e) { e.printStackTrace(); } }
	 */

	@Test
	void testFindowners() {
		try {
			mockMvc.perform(get("/owners/find")).andExpect(status().isOk()).andExpect(view().name("owners/findOwners"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		verifyNoInteractions(ownerService);
	}

	@Test
	void displayOwnerTest() {
		when(ownerService.findById(ArgumentMatchers.anyLong())).thenReturn(Owner.builder().id(1L).build());
		try {
			mockMvc.perform(get(("/owners/123"))).andExpect(status().isOk())
					.andExpect(view().name("owners/ownerDetails"))
					.andExpect(model().attribute("owner", HasPropertyWithValue.hasProperty("id", Is.is(1L))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void processFindFormReturnMany() {
		List<Owner> ownersList = owners.stream().collect(Collectors.toList());
		System.out.println(ownersList);
		when(ownerService.findAllByLastNameLikeIgnoreCase(ArgumentMatchers.anyString())).thenReturn(ownersList);
		try {
			mockMvc.perform(get("/owners")).andExpect(status().isOk()).andExpect(view().name("owners/ownersList"))
					.andExpect(model().attribute("selections", Matchers.hasSize(2)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void processFindFormReturnOne() {
		Owner testOwner = owners.iterator().next();
		when(ownerService.findAllByLastNameLikeIgnoreCase(ArgumentMatchers.anyString()))
				.thenReturn(Stream.of(testOwner).collect(Collectors.toList()));

		try {
			mockMvc.perform(get("/owners"))
				.andExpect(status()
				.is3xxRedirection())
				.andExpect(view().name("redirect:/owners/" + testOwner.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}