package com.manulsoftware.weddings.web.secure.services;

import com.manulsoftware.weddings.entity.Image;
import com.manulsoftware.weddings.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

@Controller
public class BackofficeImageController {

	@Autowired
	ImageService imageService;

	@RequestMapping(value = "/secure/uploadImage/{agencyId}", method = RequestMethod.POST)
	public @ResponseBody Image upload(@PathVariable Integer agencyId,
			MultipartHttpServletRequest request,
			HttpServletResponse httpResponse, ModelMap model,
			HttpSession session) throws IOException {
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = request.getFile(itr.next());

		final String name = mpf.getOriginalFilename();
		final byte[] source = mpf.getBytes();

		Image image = new Image();
		image.setName(name);
		image.setAgencyId(agencyId);
		image.setContent(source);
		image.setContentType(mpf.getContentType());

		image = imageService.save(image);
		
		image.setContent(null);

		return image;
	}

	@RequestMapping(value = "/secure/image/spread/{id}", method = RequestMethod.PUT)
	public @ResponseBody void setSpreadImage(@PathVariable Integer id) throws IOException {
		imageService.setSpread(id);
	}

	@RequestMapping(value = "/secure/image/list/{id}", method = RequestMethod.PUT)
	public @ResponseBody void setListImage(@PathVariable Integer id) throws IOException {
		imageService.setList(id);
	}
	
	@RequestMapping(value = "/secure/deleteImage/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteImage(@PathVariable Integer id) throws IOException {
		imageService.delete(imageService.findOne(id));
	}
}
