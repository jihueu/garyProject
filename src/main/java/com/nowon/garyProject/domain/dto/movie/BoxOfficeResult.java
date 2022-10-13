package com.nowon.garyProject.domain.dto.movie;

import java.util.List;

import lombok.Data;

@Data
public class BoxOfficeResult {
	
	//json 필드명과 동일하게 
	String boxofficeType;
	String showRange;
	List<Movie> dailyBoxOfficeList;
	

}
