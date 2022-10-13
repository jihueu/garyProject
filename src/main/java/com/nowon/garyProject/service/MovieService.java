package com.nowon.garyProject.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public interface MovieService {

	ModelAndView dailyBoxOffice();

}
