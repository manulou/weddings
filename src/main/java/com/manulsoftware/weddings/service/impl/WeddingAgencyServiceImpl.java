package com.manulsoftware.weddings.service.impl;

import com.manulsoftware.weddings.entity.WeddingAgency;
import com.manulsoftware.weddings.entity.WeddingPackage;
import com.manulsoftware.weddings.service.CountryService;
import com.manulsoftware.weddings.service.PackageAttributeService;
import com.manulsoftware.weddings.service.WeddingAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Date;
import java.util.List;

@Service
public class WeddingAgencyServiceImpl implements WeddingAgencyService {
	
	@Autowired
	private WeddingAgencyCRUDService weddingAgencyCRUDService;

	@Autowired
	private WeddingPackageCRUDService weddingPackageService;

	@Autowired
	private PackageAttributeService packageAttributeService;

	@Autowired
	private CountryService countryService;
	
	@Override
	public Integer save(final WeddingAgency agency) {
		agency.setUpdated(new Date());
		agency.setSeolink(toPrettyURL(agency.getName()));
		
		if (agency.getId() == null) {
			agency.setCreated(agency.getUpdated());
		}
		
		final Integer id = weddingAgencyCRUDService.save(agency).getId();

		final List<WeddingPackage> existingPackages = weddingPackageService.findByWeddingAgency(agency);
		if (agency.getPackages() != null) {
			agency.getPackages().stream()
					.filter(pkg -> pkg.getId() == null)
					.forEach(pkg -> pkg.setCreated(new Date()));
			agency.getPackages().stream()
					.filter(weddingPackage -> weddingPackage.getId() != null)
					.forEach(pkg -> pkg.setCreated(
							existingPackages.stream()
									.filter(existing -> existing.getId().equals(pkg.getId()))
									.map(existing -> existing.getCreated())
									.findFirst().get()));

			weddingPackageService.deleteByWeddingAgency(agency);

			agency.getPackages().stream().forEach(weddingPackage -> {
				weddingPackage.setId(null);
				weddingPackage.setWeddingAgency(agency);
				weddingPackageService.save(weddingPackage);
				if (weddingPackage.getAttributes() != null) {
					weddingPackage.getAttributes().stream().forEach(packageAttribute -> {
						packageAttribute.setId(null);
						packageAttribute.setWeddingPackage(weddingPackage);
						packageAttributeService.save(packageAttribute);
					});
				}
			});
		} else {
			weddingPackageService.deleteByWeddingAgency(agency);
		}

		final Integer agencyCount = weddingAgencyCRUDService.countByCountryIdAndDeletedAndVisible(agency.getCountry()
				.getId(), false, true);

		agency.getCountry().setHasAgencies(agencyCount > 0 || agency.isVisible());
		countryService.save(agency.getCountry());

		return id;
	}
	
	@Override
	public List<WeddingAgency> findAll() {
		return weddingAgencyCRUDService.findAll();
	}
	
	@Override
	public Page<WeddingAgency> getAgenciesPage(final Integer page, final String sortField, final String sortDirection) {
		return weddingAgencyCRUDService.findByDeletedAndVisible(false, true, new PageRequest(page, 20, Direction.fromString(sortDirection), sortField));
	}

	@Override
	public Page<WeddingAgency> getAgenciesPageAdmin(final Integer page, final String sortField, final String sortDirection) {
		return weddingAgencyCRUDService.findByDeleted(false, new PageRequest(page, 20, Direction.fromString(sortDirection)
				, sortField));
	}
	
	@Override
	public WeddingAgency findOne(final Integer id) {
		return weddingAgencyCRUDService.findOne(id);
	}

	@Override
	public WeddingAgency findOneBySeolink(String seolink) {
		return weddingAgencyCRUDService.findOneBySeolink(seolink);
	}

	@Override
	public void delete(Integer id) {
		final WeddingAgency agency = findOne(id);
		agency.setDeleted(true);
		weddingAgencyCRUDService.save(agency);

		final Integer agencyCount = weddingAgencyCRUDService.countByCountryIdAndDeletedAndVisible(
				agency.getCountry().getId(), false, true);

		agency.getCountry().setHasAgencies(agencyCount > 0);
		countryService.save(agency.getCountry());
	}
	
	private static String toPrettyURL(String string) {
	    return Normalizer.normalize(string.toLowerCase(), Form.NFD)
	        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
	        .replaceAll("[^\\p{Alnum}]+", "-");
	}
}
