package com.manulsoftware.weddings.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manulsoftware.weddings.entity.WeddingAgency;
import com.manulsoftware.weddings.service.PaginatedList;
import com.manulsoftware.weddings.service.WeddingAgencyService;

@Controller
public class AgencyRESTfulService {

	@Autowired
	WeddingAgencyService service;
	
	@RequestMapping("/allAgencies")
	@ResponseBody
	public List<WeddingAgency> allAgencies() {
		return service.findAll();
	}
	
	@RequestMapping("/getAgency/{id}")
	@ResponseBody
	public WeddingAgency getAgency(@PathVariable Integer id) {
		return service.findOne(id);
	}
	
	@RequestMapping("/searchAgencies")
	@ResponseBody
	public PaginatedList<WeddingAgency> searchAgencies(@RequestParam("page") Integer page,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDirection") String sortDirection) {
		final Page<WeddingAgency> p = service.getAgenciesPage(page, sortField, sortDirection);
		return new PaginatedList<WeddingAgency>(p, sortField, sortDirection);
	}
}
