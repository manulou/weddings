package com.manulsoftware.weddings.service;

import com.manulsoftware.weddings.entity.WeddingAgency;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WeddingAgencyService {
	
	// agencies
	
	Integer save(final WeddingAgency agency); 
	
	List<WeddingAgency> findAll();
	
	Page<WeddingAgency> getAgenciesPage(final Integer page, final String sortField, final String sortDirection);

	Page<WeddingAgency> getAgenciesPageAdmin(final Integer page, final String sortField, final String sortDirection);
	
	WeddingAgency findOne(final Integer id);

	WeddingAgency findOneBySeolink(final String seolink);
	
	void delete(final Integer id);
	
}