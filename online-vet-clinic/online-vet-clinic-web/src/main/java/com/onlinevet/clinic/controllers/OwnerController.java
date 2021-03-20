package com.onlinevet.clinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnerController {
	@RequestMapping({"","/","/ownerList","/ownerList.html"})
	public String listOwners() {
		return "owners/ownerList";
	}
}
