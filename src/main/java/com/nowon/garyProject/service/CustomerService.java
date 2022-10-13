package com.nowon.garyProject.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface CustomerService {

	void list(Model model, String divname);
	void list(Model model, int divno);
	ModelAndView list(ModelAndView mv, int divno);

}
