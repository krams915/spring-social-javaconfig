package org.krams.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MediatorController {

	@RequestMapping
	public String getHomePage(ModelMap model) {
		model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication().getName());
		return "welcome";
	}
}