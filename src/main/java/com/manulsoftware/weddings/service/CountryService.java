package com.manulsoftware.weddings.service;

import com.manulsoftware.weddings.entity.Country;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryService extends Repository<Country, Short> {

	@SuppressWarnings("unchecked")
	Country save(final Country agency);

	public Country findOne(final Short id);

	public List<Country> findAllByOrderByName();

	public List<Country> findByHasAgenciesOrderByName(final boolean hasAgencies);
}
