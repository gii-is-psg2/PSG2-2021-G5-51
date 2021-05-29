package org.springframework.samples.petclinic.web;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class AboutController {

	@RequestMapping({"/","/about"})
	public String about(Map<String, Object> model) {	    
		return "about";
	}
	
}
