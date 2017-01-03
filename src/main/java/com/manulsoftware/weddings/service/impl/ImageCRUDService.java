package com.manulsoftware.weddings.service.impl;

import com.manulsoftware.weddings.entity.Image;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImageCRUDService extends Repository<Image, Integer> {

	public Image save(Image image);

	public void delete(Image image);
	
	public Image findOne(Integer id);

	public List<Image> findByAgencyIdAndSpread(Integer agencyId, boolean spread);

	public List<Image> findByAgencyIdAndList(Integer agencyId, boolean list);

	public List<Image> findByAgencyIdAndSpreadAndThumbnail(Integer agencyId, boolean spread, boolean thumbnail);

	public List<Image> findByAgencyIdAndListAndThumbnail(Integer agencyId, boolean list, boolean thumbnail);
	
	public List<Image> findByAgencyIdAndThumbnail(Integer agencyId, boolean thumbnail);
}
