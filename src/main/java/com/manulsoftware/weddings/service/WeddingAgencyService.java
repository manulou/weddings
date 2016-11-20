package com.manulsoftware.weddings.service;

import com.manulsoftware.weddings.entity.WeddingAgency;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WeddingAgencyService {

//	@PreAuthorize("hasRole('ADMIN')")
	Integer save(final WeddingAgency agency); 
	
	List<WeddingAgency> findAll();
	
	Page<WeddingAgency> getAgenciesPage(final Integer page, final String sortField, final String sortDirection);

//	@PreAuthorize("hasRole('ADMIN')")
	Page<WeddingAgency> getAgenciesPageAdmin(final Integer page, final String sortField, final String sortDirection);
	
	WeddingAgency findOne(final Integer id);

	WeddingAgency findOneBySeolink(final String seolink);

//	@PreAuthorize("hasRole('ADMIN')")
	void delete(final Integer id);
	
}