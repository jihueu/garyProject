package com.nowon.garyProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nowon.garyProject.service.MovieService;
import com.nowon.garyProject.service.impl.MovieServieceProc;

@RestController
public class MovieController {
	
	MovieService service= new MovieServieceProc();
	
	//단순페이지이동
	@GetMapping("/common/movies")
	public ModelAndView page() {
		return new ModelAndView("movie/page");
	}
	@GetMapping("/common/movie-list")
	public ModelAndView dailyBoxOffice() {
		return service.dailyBoxOffice();
	}

}
