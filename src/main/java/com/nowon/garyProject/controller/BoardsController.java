package com.nowon.garyProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nowon.garyProject.domain.entity.Div;

@Controller
public class BoardsController {
	
	@GetMapping("/boards")
	public String list() {
		return "board/list";
	}
	
	
	///FAQ연결///
	
	@GetMapping("/faqBoard")
	public String faqwirte(Model model) {
		
		 model.addAttribute("cataD", Div.values());
		return "admin/cus/faqBoard";
	}
	
	
}
