package com.manulsoftware.weddings.service.impl;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Date;
import java.util.List;

import com.manulsoftware.weddings.entity.PackageAttribute;
import com.manulsoftware.weddings.entity.WeddingPackage;
import com.manulsoftware.weddings.service.PackageAttributeService;
import com.manulsoftware.weddings.service.WeddingPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.manulsoftware.weddings.entity.WeddingAgency;
import com.manulsoftware.weddings.service.WeddingAgencyService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WeddingAgencyServiceImpl implements WeddingAgencyService {
	
	@Autowired
	private WeddingAgencyCRUDService weddingAgencyCRUDService;

	@Autowired
	private WeddingPackageService weddingPackageService;

	@Autowired
	private PackageAttributeService packageAttributeService;
	
	@Override
	public Integer save(final WeddingAgency agency) {
		agency.setUpdated(new Date());
		agency.setSeolink(toPrettyURL(agency.getName()));
		
		if (agency.getId() == null) {
			agency.setCreated(agency.getUpdated());
		}
		
		final Integer id = weddingAgencyCRUDService.save(agency).getId();

		weddingPackageService.deleteByWeddingAgency(agency);

		if (agency.getPackages() != null) {
			for (WeddingPackage weddingPackage : agency.getPackages()) {
				weddingPackage.setId(null);
				weddingPackage.setWeddingAgency(agency);
				weddingPackageService.save(weddingPackage);
				if (weddingPackage.getAttributes() != null) {
					for (PackageAttribute packageAttribute : weddingPackage.getAttributes()) {
						packageAttribute.setId(null);
						packageAttribute.setWeddingPackage(weddingPackage);
						packageAttributeService.save(packageAttribute);
					}
				}
			}
		}

		return id;
	}
	
	@Override
	public List<WeddingAgency> findAll() {
		return weddingAgencyCRUDService.findAll();
	}
	
	@Override
	public Page<WeddingAgency> getAgenciesPage(final Integer page, final String sortField, final String sortDirection) {
		return weddingAgencyCRUDService.findByDeleted(false, new PageRequest(page, 20, Direction.fromString(sortDirection), sortField));
	}
	
	@Override
	public WeddingAgency findOne(final Integer id) {
		return weddingAgencyCRUDService.findOne(id);
	}
	
	@Override
	public void delete(Integer id) {
		final WeddingAgency agency = findOne(id);
		agency.setDeleted(true);
		weddingAgencyCRUDService.save(agency);
	}
	
	private static String toPrettyURL(String string) {
	    return Normalizer.normalize(string.toLowerCase(), Form.NFD)
	        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
	        .replaceAll("[^\\p{Alnum}]+", "-");
	}
}
