package com.manulsoftware.weddings.web.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

	@RequestMapping({"/", "/secure", "/secure/**", "/agency/**"})
	public String landingPage() {
		return "landingPage";
	}
}
