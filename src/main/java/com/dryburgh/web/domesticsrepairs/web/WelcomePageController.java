package com.dryburgh.web.domesticsrepairs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomePageController {

	@Autowired
	public WelcomePageController() {
		
	}
	
	@GetMapping
	public String getHomeData() {
		return "Hello World";
	}
	
}
