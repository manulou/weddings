package com.manulsoftware.weddings.web.secure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manulsoftware.weddings.entity.WeddingAgency;
import com.manulsoftware.weddings.service.PaginatedList;
import com.manulsoftware.weddings.service.WeddingAgencyService;

@Controller
public class BackofficeRESTfulService {

	@Autowired
	WeddingAgencyService service;
	
	@RequestMapping("/secure/deleteAgency/{id}")
	@ResponseBody
	public void deleteAgency(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@RequestMapping(value = "/secure/saveAgency", method = RequestMethod.POST)
	@ResponseBody
	public WeddingAgency saveAgency(@RequestBody WeddingAgency agency) {
		service.save(agency);
		return agency;
	}
	
	@RequestMapping("/secure/searchAgencies")
	@ResponseBody
	public PaginatedList<WeddingAgency> searchAgencies(@RequestParam("page") Integer page,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDirection") String sortDirection) {
		final Page<WeddingAgency> p = service.getAgenciesPage(page, sortField, sortDirection);
		return new PaginatedList<WeddingAgency>(p, sortField, sortDirection);
	}
}
