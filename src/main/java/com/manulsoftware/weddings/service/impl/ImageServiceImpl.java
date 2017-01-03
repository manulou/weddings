package com.manulsoftware.weddings.service.impl;

import com.manulsoftware.weddings.entity.Image;
import com.manulsoftware.weddings.service.ImageService;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
	
	private static final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);
	
	@Autowired
	private ImageCRUDService imageCRUDService;
	
	@Override
	public Image save(final Image image) {
		image.setThumbnail(false);
		imageCRUDService.save(image);
		
		final Image thumbnail = generateThumbnail(image);
		if (thumbnail != null) {
			imageCRUDService.save(thumbnail);
			return thumbnail;
		}
		
		return image;
	}
	
	private Image generateThumbnail(final Image image) {
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			Thumbnails.of(new ByteArrayInputStream(image.getContent())).size(260, 260).toOutputStream(bos);
			final Image thumbnail = new Image();
			thumbnail.setAgencyId(image.getAgencyId());
			thumbnail.setContent(bos.toByteArray());
			thumbnail.setName("small_" + image.getName());
			thumbnail.setRelatedImageId(image.getId());
			thumbnail.setContentType(image.getContentType());
			thumbnail.setThumbnail(true);
			thumbnail.setList(false);
			thumbnail.setSpread(false);
			return thumbnail;
		} catch (IOException e) {
			logger.error("Error generating thumbnail", e);
			return null;
		}
	}
	
	@Override
	public Image findOne(Integer id) {
		return imageCRUDService.findOne(id);
	}
	
	@Override
	public List<Image> findByAgencyId(Integer agencyId) {
		//TODO: fix it properly
		final List<Image> images = imageCRUDService.findByAgencyIdAndThumbnail(agencyId, true);
		images.forEach((image -> image.setContent(null)));
		return images;
	}
	
	@Override
	public void delete(final Image image) {
		imageCRUDService.delete(image);
		if (image.getRelatedImageId() != null) {
			final Image related = new Image();
			related.setId(image.getRelatedImageId());
			imageCRUDService.delete(related);
		}
	}

	@Override
	public void setSpread(Integer id) {
		final Image thumbnail = findOne(id);
		final Image image = findOne(thumbnail.getRelatedImageId());
		final List<Image> existingSpreadImages = imageCRUDService.findByAgencyIdAndSpread(image.getAgencyId(), true);
		if (existingSpreadImages != null && existingSpreadImages.size() > 0) {
			existingSpreadImages.stream().forEach(img -> { img.setSpread(false); imageCRUDService.save(img); });
		}
		thumbnail.setSpread(true);
		image.setSpread(true);
		imageCRUDService.save(thumbnail);
		imageCRUDService.save(image);
	}

	@Override
	public void setList(Integer id) {
		final Image thumbnail = findOne(id);
		final Image image = findOne(thumbnail.getRelatedImageId());
		final List<Image> existingListImages = imageCRUDService.findByAgencyIdAndList(image.getAgencyId(), true);
		if (existingListImages != null && existingListImages.size() > 0) {
			existingListImages.stream().forEach(img -> { img.setList(false); imageCRUDService.save(img); });
		}
		thumbnail.setList(true);
		image.setList(true);
		imageCRUDService.save(thumbnail);
		imageCRUDService.save(image);
	}

	@Override
	public Image getSpread(Integer agencyId) {
		final List<Image> existingSpreadImages = imageCRUDService.findByAgencyIdAndSpreadAndThumbnail(agencyId, true, false);
		return existingSpreadImages != null && existingSpreadImages.size() > 0 ? existingSpreadImages.get(0) : null;
	}

	@Override
	public Image getList(Integer agencyId) {
		final List<Image> existingListImages = imageCRUDService.findByAgencyIdAndListAndThumbnail(agencyId, true, false);
		return existingListImages != null && existingListImages.size() > 0 ? existingListImages.get(0) : null;
	}
}
