package com.manulsoftware.weddings.web.services;

import com.manulsoftware.weddings.entity.Attribute;
import com.manulsoftware.weddings.entity.Category;
import com.manulsoftware.weddings.entity.WeddingAgency;
import com.manulsoftware.weddings.service.AttributeService;
import com.manulsoftware.weddings.service.CategoryService;
import com.manulsoftware.weddings.service.PaginatedList;
import com.manulsoftware.weddings.service.WeddingAgencyService;
import com.manulsoftware.weddings.service.impl.WeddingPackageCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AgencyRESTfulService {

	@Autowired
	WeddingAgencyService weddingAgencyService;

	@Autowired
	WeddingPackageCRUDService weddingPackageService;

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
		final WeddingAgency agency = weddingAgencyService.findOne(id);
		filterPackages(agency);
		return agency;
	}

	@RequestMapping("/getAgencyBySeolink/{seolink}")
	@ResponseBody
	public WeddingAgency getAgencyBySeolink(@PathVariable String seolink) {
		final WeddingAgency agency = weddingAgencyService.findOneBySeolink(seolink);
		filterPackages(agency);
		return agency;
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
		p.forEach(agency -> filterPackages(agency));
		return new PaginatedList<WeddingAgency>(p, sortField, sortDirection);
	}

	private void filterPackages(final WeddingAgency agency) {
		agency.setPackages(agency.getPackages().stream().filter(pkg -> pkg.isVisible()).collect(Collectors.toList()));
	}
}
