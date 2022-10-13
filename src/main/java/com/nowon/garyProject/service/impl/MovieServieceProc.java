package com.nowon.garyProject.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowon.garyProject.domain.dto.movie.DailyBoxOfficeDTO;
import com.nowon.garyProject.service.MovieService;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import kr.or.kobis.kobisopenapi.consumer.rest.exception.OpenAPIFault;

public class MovieServieceProc implements MovieService {

	@Override
	public ModelAndView dailyBoxOffice() {
		//어제 now().minusDays(1)
		LocalDate yesterday = LocalDate.now().minusDays(1);
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyymmdd");
		//kobis오픈api에서 요구하는 날짜 패턴
		String targetDt=yesterday.format(formatter);//yyyymmdd
		String key="790dcaa10d364d66b6138603f1061602";
		String itemPerPage=null;
		String multiMovieYn=null;
		String repNationCd=null;
		String WideAreaCd=null;
		
		KobisOpenAPIRestService kobisService=new KobisOpenAPIRestService(key);
		DailyBoxOfficeDTO data=null;
		try {
			//java의 class타입으로 매핑합니다. DTO에 매핑한다는것
			String jsondata = kobisService.getDailyBoxOffice(true, targetDt, itemPerPage, multiMovieYn, repNationCd, WideAreaCd);
			//json
			ObjectMapper mapper=new ObjectMapper();
			data=mapper.readValue(jsondata, DailyBoxOfficeDTO.class);
			
		} catch (OpenAPIFault e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ModelAndView mv=new ModelAndView("movie/mlist");
		mv.addObject("mlist", data.getBoxOfficeResult().getDailyBoxOfficeList());
		return mv;
	}

}
