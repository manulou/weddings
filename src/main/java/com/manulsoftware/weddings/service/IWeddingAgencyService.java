package com.manulsoftware.weddings.service;

import java.util.List;

import com.manulsoftware.weddings.entity.Image;
import com.manulsoftware.weddings.entity.WeddingAgency;

public interface IWeddingAgencyService {
	
	// agencies
	
	Integer saveAgency(final WeddingAgency agency); 
	
	List<WeddingAgency> getAllAgencies();
	
	PaginatedList<WeddingAgency> getAgenciesList(PaginatedList<WeddingAgency> pagination);
	
	WeddingAgency loadAgency(final Integer id);
	
	void deleteAgency(final Integer id);
	
	// countries
	
	List<String> getAllCountries();
	
	// images

	Image createImage(Image image);

	void deleteImage(Integer id);
	
	Image loadImage(Integer id);
	
	List<Image> loadThumbnailsForAgency(Integer agencyId);
}
