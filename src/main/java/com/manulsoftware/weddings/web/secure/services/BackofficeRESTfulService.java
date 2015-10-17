package com.manulsoftware.weddings.web.secure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manulsoftware.weddings.entity.WeddingAgency;
import com.manulsoftware.weddings.service.IWeddingAgencyService;
import com.manulsoftware.weddings.service.PaginatedList;

@Controller
public class BackofficeRESTfulService {

	@Autowired
	IWeddingAgencyService service;
	
	@RequestMapping("/secure/deleteAgency/{id}")
	@ResponseBody
	public void deleteAgency(@PathVariable Integer id) {
		service.deleteAgency(id);
	}
	
	@RequestMapping(value = "/secure/saveAgency", method = RequestMethod.POST)
	@ResponseBody
	public WeddingAgency saveAgency(@RequestBody WeddingAgency agency) {
		service.saveAgency(agency);
		return agency;
	}
	
	@RequestMapping("/secure/searchAgencies")
	@ResponseBody
	public PaginatedList<WeddingAgency> searchAgencies(@RequestParam("page") Integer page,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDirection") String sortDirection) {
		PaginatedList<WeddingAgency> pagination = new PaginatedList<>();
		pagination.setCurrentPage(page);
		pagination.setSortField(sortField);
		pagination.setSortDirection(sortDirection);
		pagination.setPageSize(20);
		return service.getAgenciesList(pagination);
	}
}
