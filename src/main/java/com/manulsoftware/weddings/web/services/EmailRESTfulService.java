package com.manulsoftware.weddings.web.services;

import com.manulsoftware.weddings.entity.Email;
import com.manulsoftware.weddings.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;

@Controller
public class EmailRESTfulService {

	@Autowired
	EmailService emailService;
	
	@RequestMapping(value = "/enquire", method = RequestMethod.PUT)
	@ResponseBody
	public String getCountries(@RequestBody Email email) throws MessagingException {
		emailService.sendEmail(email);
		return "OK";
	}
}
