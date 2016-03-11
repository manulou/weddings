package com.manulsoftware.weddings.service;

import java.util.List;

import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import com.manulsoftware.weddings.entity.Country;

@Service
public interface CountryService extends Repository<Country, Short> {
	
	public List<Country> findAll();
}
