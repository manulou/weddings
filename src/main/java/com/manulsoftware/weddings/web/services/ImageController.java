package com.manulsoftware.weddings.web.services;

import com.manulsoftware.weddings.entity.Image;
import com.manulsoftware.weddings.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
public class ImageController {
	
	@Autowired
	ImageService imageService;
	
	@RequestMapping("/agency/{agencyId}/thumbnails")
	@ResponseBody
	public List<Image> thumbnails(@PathVariable Integer agencyId) {
		return imageService.findByAgencyId(agencyId);
	}
	
	@RequestMapping("/image/{id}")
	public ResponseEntity loadImage(@PathVariable Integer id) {
		return imageResponse(imageService.findOne(id));
	}

	@RequestMapping("/agency/{agencyId}/spread")
	@ResponseBody
	public ResponseEntity spread(@PathVariable Integer agencyId) {
		return imageResponse(imageService.getSpread(agencyId));
	}

	@RequestMapping("/agency/{agencyId}/list")
	@ResponseBody
	public ResponseEntity list(@PathVariable Integer agencyId) {
		return imageResponse(imageService.getList(agencyId));
	}

	private ResponseEntity imageResponse(final Image image) {
		if (image == null) {
			return notFound();
		} else {
			return imageEntity(image);
		}
	}

	private ResponseEntity notFound() {
		return ResponseEntity.notFound().build();
	}

	private ResponseEntity<InputStreamResource> imageEntity(final Image image) {
		return ResponseEntity
				.ok()
				.contentLength(image.getContent().length)
				.contentType(MediaType.parseMediaType(image.getContentType()))
				.body(new InputStreamResource(new ByteArrayInputStream(image
						.getContent())));
	}
}
