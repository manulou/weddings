package com.manulsoftware.weddings.web.services;

import com.manulsoftware.weddings.entity.Country;
import com.manulsoftware.weddings.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CountryRESTfulService {

	@Autowired
	CountryService countryService;
	
	@RequestMapping("/getCountries")
	@ResponseBody
	public List<Country> getCountries() {
		return countryService.findAllByOrderByName();
	}

	@RequestMapping("/getCountriesForFilter")
	@ResponseBody
	public List<Country> getCountriesForFilter() {
		return countryService.findByHasAgenciesOrderByName(true);
	}
}
