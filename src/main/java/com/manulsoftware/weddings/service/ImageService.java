package com.manulsoftware.weddings.service;

import java.util.List;

import com.manulsoftware.weddings.entity.Image;

public interface ImageService {
	
	Image save(Image image);

	void delete(final Image image);
	
	Image findOne(Integer id);
	
	List<Image> findByAgencyId(Integer agencyId);
}
