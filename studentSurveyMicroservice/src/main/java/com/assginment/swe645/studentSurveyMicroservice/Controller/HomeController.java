package com.assginment.swe645.studentSurveyMicroservice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("")
	public String home() {
		return "Welcome to Student Survey Microservice!!";
	}
}
