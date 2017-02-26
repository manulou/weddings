package com.manulsoftware.weddings.web.services;

import com.google.gson.Gson;
import com.manulsoftware.weddings.entity.WeddingPackage;
import com.manulsoftware.weddings.service.PaginatedList;
import com.manulsoftware.weddings.service.WeddingPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class WeddingPackageRESTfulService {

	@Autowired
	WeddingPackageService weddingPackageService;
	
	@RequestMapping(value = "/searchPackages", method = RequestMethod.GET)
	@ResponseBody
	public String searchPackages(
			@RequestParam Integer page,
			@RequestParam String sortField,
			@RequestParam String sortDirection,
			@RequestParam(required = false) Short countryId,
			@RequestParam(required = false) BigDecimal maxPrice) {
		final SearchFilter searchFilter = new SearchFilter(page, sortField, sortDirection, countryId, maxPrice);
		final Page<WeddingPackage> p = weddingPackageService.searchPackages(searchFilter);
		return serializeToJson(new PaginatedList<WeddingPackage>(p, searchFilter.getSortField(), searchFilter
				.getSortDirection()));
	}

	@RequestMapping(value = "/mostRecentPackages", method = RequestMethod.GET)
	@ResponseBody
	public String mostRecentPackages() {
		return new Gson().toJson(processPackages(weddingPackageService.getMostRecentPackages()));
	}

	private String serializeToJson(final PaginatedList<WeddingPackage> page) {
		page.setList(processPackages(page.getList()));
		return new Gson().toJson(page);
	}

	private List<WeddingPackage> processPackages(final List<WeddingPackage> packages) {
		if (packages != null) {
			packages.stream().forEach(weddingPackage -> {
				weddingPackage.setAttributes(null);
				weddingPackage.getWeddingAgency().setPackages(null);
			});
		}
		return packages;
	}
}
