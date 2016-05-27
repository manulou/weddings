package com.manulsoftware.weddings.web.secure.services;

import com.manulsoftware.weddings.entity.Attribute;
import com.manulsoftware.weddings.entity.Category;
import com.manulsoftware.weddings.entity.WeddingAgency;
import com.manulsoftware.weddings.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BackofficeRESTfulService {

	@Autowired
	WeddingAgencyService weddingAgencyService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	AttributeService attributeService;

	@Autowired
	WeddingPackageService weddingPackageService;
	
	@RequestMapping("/secure/deleteAgency/{id}")
	@ResponseBody
	public void deleteAgency(@PathVariable Integer id) {
		weddingAgencyService.delete(id);
	}
	
	@RequestMapping(value = "/secure/saveAgency", method = RequestMethod.POST)
	@ResponseBody
	public WeddingAgency saveAgency(@RequestBody WeddingAgency agency) {
		weddingAgencyService.save(agency);
		return agency;
	}

	@RequestMapping(value = "/secure/saveAttribute", method = RequestMethod.POST)
	@ResponseBody
	public Attribute saveAttribute(@RequestBody Attribute attribute) {
		attributeService.save(attribute);
		return attribute;
	}

	@RequestMapping(value = "/secure/saveCategory", method = RequestMethod.POST)
	@ResponseBody
	public Category saveCategory(@RequestBody Category category) {
		categoryService.save(category);
		return category;
	}

	@RequestMapping(value = "/secure/deletePackage/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deletePackage(@PathVariable Integer id) {
		weddingPackageService.delete(weddingPackageService.findOne(id));
	}
	
	@RequestMapping("/secure/searchAgencies")
	@ResponseBody
	public PaginatedList<WeddingAgency> searchAgencies(@RequestParam("page") Integer page,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDirection") String sortDirection) {
		final Page<WeddingAgency> p = weddingAgencyService.getAgenciesPage(page, sortField, sortDirection);
		return new PaginatedList<WeddingAgency>(p, sortField, sortDirection);
	}
}
