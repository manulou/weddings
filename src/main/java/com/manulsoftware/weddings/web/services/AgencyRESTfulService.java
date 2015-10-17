package com.manulsoftware.weddings.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manulsoftware.weddings.entity.WeddingAgency;
import com.manulsoftware.weddings.service.IWeddingAgencyService;
import com.manulsoftware.weddings.service.PaginatedList;

@Controller
public class AgencyRESTfulService {

	@Autowired
	IWeddingAgencyService service;
	
	@RequestMapping("/allAgencies")
	@ResponseBody
	public List<WeddingAgency> allAgencies() {
		return service.getAllAgencies();
	}
	
	@RequestMapping("/getAgency/{id}")
	@ResponseBody
	public WeddingAgency getAgency(@PathVariable Integer id) {
		return service.loadAgency(id);
	}
	
	@RequestMapping("/searchAgencies")
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
