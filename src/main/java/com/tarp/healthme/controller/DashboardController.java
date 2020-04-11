package com.tarp.healthme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

	@GetMapping("/doctor")
	public String doctorDashboard() {
		return "doctor/dashboard";
	}
	
	@GetMapping("/client")
	public String clientDashboard() {
		return "client/dashboard";
	}
}