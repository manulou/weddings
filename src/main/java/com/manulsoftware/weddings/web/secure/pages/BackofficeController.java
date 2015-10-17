package com.manulsoftware.weddings.web.secure.pages;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BackofficeController {

	@RequestMapping("/secure")
	public String agencies() {
		return "secure/secureAgencies";
	}
	
	@RequestMapping("/secure/agency/{id}")
	public String agencyDetails(@PathVariable String id, ModelMap model, HttpSession session) {
		model.addAttribute("id", id);
		return "secure/secureAgencyDetails";
	}
	
	@RequestMapping("/secure/agency/new")
	public String newAgency() {
		return "secure/secureAgencyDetails";
	}
}
