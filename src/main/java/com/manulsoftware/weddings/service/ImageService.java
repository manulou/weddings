package com.manulsoftware.weddings.service;

import com.manulsoftware.weddings.entity.Image;

import java.util.List;

public interface ImageService {

//	@PreAuthorize("hasRole('ADMIN')")
	Image save(Image image);

//	@PreAuthorize("hasRole('ADMIN')")
	void delete(final Image image);
	
	Image findOne(Integer id);
	
	List<Image> findByAgencyId(Integer agencyId);
}
