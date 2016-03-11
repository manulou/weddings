package com.manulsoftware.weddings.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.manulsoftware.weddings.entity.WeddingAgency;

public interface WeddingAgencyService {
	
	// agencies
	
	Integer save(final WeddingAgency agency); 
	
	List<WeddingAgency> findAll();
	
	Page<WeddingAgency> getAgenciesPage(final Integer page, final String sortField, final String sortDirection);
	
	WeddingAgency findOne(final Integer id);
	
	void delete(final Integer id);
	
}