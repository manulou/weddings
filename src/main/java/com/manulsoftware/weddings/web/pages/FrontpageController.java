package com.manulsoftware.weddings.web.pages;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontpageController {

	@RequestMapping("/")
	public String agencies() {
		return "agencies";
	}
	
	@RequestMapping("/agency/{id}")
	public String agencyDetails(@PathVariable String id, ModelMap model, HttpSession session) {
		model.addAttribute("id", id);
		return "agencyDetails";
	}
}
