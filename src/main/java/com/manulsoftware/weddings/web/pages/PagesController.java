package com.manulsoftware.weddings.web.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontpageController {

	@RequestMapping({"/", "/agency/{id}"})
	public String agencies(final Model model) {
		model.addAttribute("application", "app");
		return "agencies";
	}
}
