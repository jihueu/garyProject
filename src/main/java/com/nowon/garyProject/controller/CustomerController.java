package com.nowon.garyProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nowon.garyProject.service.CustomerService;

@Controller 
public class CustomerController {

	@Autowired
	CustomerService service;

	@GetMapping("/common/customer/{divno}")
	public String customerList(Model model, @PathVariable int divno) {
		service.list(model, divno);
		return "cus/faq/customerlist";
		
	}
	// 단순 페이지 이동
	@GetMapping("/common/customer")
	public String customer() {
		return "cus/faq/cusindex";
		// RestController 벗어나는 방법2
	}
	
	/*@RestController 일때는 // Controller + @ResponseBody
	 * @GetMapping("/common/customer/{divno}") public ModelAndView
	 * customerList2(ModelAndView mv, @PathVariable int divno) {
	 * 
	 * return service.list(mv, divno);
	 * 
	 * } // 단순 페이지 이동을 위해 ModelAndView를 적용한 경우
	 * 
	 * @GetMapping("/common/customer") public ModelAndView customer2(ModelAndView
	 * mv) { mv.setViewName("cus/faq/index"); return mv; // RestController 벗어나는 방법2
	 * }
	 */


	
	 
	 
}
