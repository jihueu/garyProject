package com.nowon.garyProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	@GetMapping("/signin")
	public String login() {
		return "sign/signin";
	}
	
	@GetMapping("/loginPage")
	public String loginPage() {
		return "redirect:/";
	}
	
	@GetMapping("/common/signup")
	public String signup() {
		return "sign/signup";
	}
	
	//굿즈페이지이동
	@GetMapping("/common/goodspage")
	public String goodsPage() {
		return "goodspage/goodspage";
	}
	
	@GetMapping("/common/location")
	public String locationPage() {
		return"location/location";
	}
	
	
}
