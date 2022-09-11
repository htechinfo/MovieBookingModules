package com.movie.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/")
public class HomeController {

	@GetMapping
	public String getHomePage() {
		return " Movie Ticket Booking System(Proof Of Concept) <br><br>" +
				"<a href='/swagger-ui/index.html'>" +
				"Launch Swagger API" +
				"<a>";
	}

}
