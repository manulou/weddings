package com.manulsoftware.weddings.web.services;

import java.util.List;

import com.manulsoftware.weddings.entity.Attribute;
import com.manulsoftware.weddings.entity.Category;
import com.manulsoftware.weddings.entity.WeddingPackage;
import com.manulsoftware.weddings.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manulsoftware.weddings.entity.WeddingAgency;

@Controller
public class AgencyRESTfulService {

	@Autowired
	WeddingAgencyService weddingAgencyService;

	@Autowired
	WeddingPackageService weddingPackageService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	AttributeService attributeService;
	
	@RequestMapping("/allAgencies")
	@ResponseBody
	public List<WeddingAgency> allAgencies() {
		return weddingAgencyService.findAll();
	}
	
	@RequestMapping("/getAgency/{id}")
	@ResponseBody
	public WeddingAgency getAgency(@PathVariable Integer id) {
		return weddingAgencyService.findOne(id);
	}

	@RequestMapping("/getCategories")
	@ResponseBody
	public List<Category> getCategories() {
		return categoryService.findAllByOrderByPriority();
	}

	@RequestMapping("/getAttributes")
	@ResponseBody
	public List<Attribute> getAttributes() {
		return attributeService.findAllByOrderByName();
	}
	
	@RequestMapping("/searchAgencies")
	@ResponseBody
	public PaginatedList<WeddingAgency> searchAgencies(@RequestParam("page") Integer page,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDirection") String sortDirection) {
		final Page<WeddingAgency> p = weddingAgencyService.getAgenciesPage(page, sortField, sortDirection);
		return new PaginatedList<WeddingAgency>(p, sortField, sortDirection);
	}
}
