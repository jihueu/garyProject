package com.nowon.garyProject.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.nowon.garyProject.domain.dto.cus.FaqListDTO;
import com.nowon.garyProject.domain.entity.Div;
import com.nowon.garyProject.domain.entity.FaqEntityRepository;
import com.nowon.garyProject.service.CustomerService;

@Service
public class CutomerServiceImpl implements CustomerService {

	@Autowired
	private FaqEntityRepository repository;
	
	@Override
	public void list(Model model, String div) {
		
		Div division=null;
		Div[] divs= Div.values();
		
		for(Div di:divs) {
			if(div.equals(di.getUrl())) {
				division=di;
			}
		}
		
		model.addAttribute("clist", repository.findByDiv(division).stream()
				.map(FaqListDTO::new)
				.collect(Collectors.toList()));
	}

	@Override
	public void list(Model model, int divno) {
		Div division=null;
		Div[] divs= Div.values();
		
		for(Div di:divs) {
			if(divno==di.ordinal()) {
				division=di;
			}
		}
		
		model.addAttribute("clist", repository.findByDiv(division).stream()
				.map(FaqListDTO::new)
				.collect(Collectors.toList()));
		
	}

	@Override
	public ModelAndView list(ModelAndView mv, int divno) {
		Div division=null;
		Div[] divs= Div.values();
		
		for(Div di:divs) {
			if(divno==di.ordinal()) {
				division=di;
			}
		}
		
		mv.addObject("clist", repository.findByDiv(division).stream()
				.map(FaqListDTO::new)
				.collect(Collectors.toList()));
		mv.setViewName("cus/faq/customerlist");
		return mv;
	}

}
