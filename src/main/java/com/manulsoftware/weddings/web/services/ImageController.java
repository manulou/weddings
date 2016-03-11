package com.manulsoftware.weddings.web.services;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manulsoftware.weddings.entity.Image;
import com.manulsoftware.weddings.service.ImageService;

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
	public ResponseEntity<InputStreamResource> loadImage(@PathVariable Integer id, ModelMap model, HttpSession session) {
		final Image image = imageService.findOne(id);
		return ResponseEntity
				.ok()
				.contentLength(image.getContent().length)
				.contentType(MediaType.parseMediaType(image.getContentType()))
				.body(new InputStreamResource(new ByteArrayInputStream(image
						.getContent())));
	}
}
