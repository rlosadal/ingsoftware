package co.com.app.solicitudes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	
	@GetMapping("/accessdenied")
	public String getAccessDeniedPage() {
		
		return "accessdenied";
	
	}
	
	@GetMapping("/layout")
	public String layout() {
		
		return "layout/layout";
	
	}
	
}
