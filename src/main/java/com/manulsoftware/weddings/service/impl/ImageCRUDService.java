package com.manulsoftware.weddings.service.impl;

import java.util.List;

import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import com.manulsoftware.weddings.entity.Image;

@Service
public interface ImageCRUDService extends Repository<Image, Integer> {
	
	public Image save(Image image);

	public void delete(Image image);
	
	public Image findOne(Integer id);
	
	public List<Image> findByAgencyIdAndThumbnail(Integer agencyId, boolean thumbnail);
}
