package com.onlinevet.clinic.formatter;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.onlinevet.clinic.model.PetType;
import com.onlinevet.clinic.service.PetTypeService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
//@Component
public class PetTypeFormatter implements Formatter<PetType> {

	@Autowired
	private final PetTypeService petTypeService;

	@Override
	public String print(PetType petType, Locale locale) {
		return petType.getName();
	}

	@Override
	public PetType parse(String text, Locale locale) throws ParseException {

		Collection<PetType> findPetTypes = petTypeService.findAll();
		for (PetType
				type : findPetTypes) {
			if (type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);

		
/*		return petTypeService.findAll().parallelStream()
					.filter(p -> p.getName().equals(text))
					.findAny()
					.orElseThrow(() -> new ParseException("type not found: " + text, 0));*/
	}
}
