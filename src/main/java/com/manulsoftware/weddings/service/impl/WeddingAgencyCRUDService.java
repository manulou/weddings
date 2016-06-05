package com.manulsoftware.weddings.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.manulsoftware.weddings.entity.WeddingAgency;

@Service
public interface WeddingAgencyCRUDService extends PagingAndSortingRepository<WeddingAgency, Integer> {
	
	@SuppressWarnings("unchecked")
	WeddingAgency save(final WeddingAgency agency); 
	
	List<WeddingAgency> findAll();
	
	Page<WeddingAgency> findByDeletedAndVisible(boolean deleted, boolean visible, Pageable pageable);

	Page<WeddingAgency> findByDeleted(boolean deleted, Pageable pageable);
	
	WeddingAgency findOne(final Integer id);
}