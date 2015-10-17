package com.manulsoftware.weddings.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manulsoftware.weddings.entity.Country;
import com.manulsoftware.weddings.service.IWeddingAgencyService;

@Controller
public class CountryRESTfulService {

	@Autowired
	IWeddingAgencyService service;
	
	@RequestMapping("/getCountries")
	@ResponseBody
	public List<Country> allAgencies() {
		return service.getAllCountries();
	}
	
}
