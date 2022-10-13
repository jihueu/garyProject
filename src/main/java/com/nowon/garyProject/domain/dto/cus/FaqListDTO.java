package com.nowon.garyProject.domain.dto.cus;

import com.nowon.garyProject.domain.entity.Div;
import com.nowon.garyProject.domain.entity.FaqEntity;

import lombok.Getter;

@Getter
public class FaqListDTO {

	private long faqNo;
	
	private String question;
	private String answer;
	private Div div;
	
	public FaqListDTO(FaqEntity e) {
		
		faqNo=e.getFaqNo();
		question=e.getQuestion();
		answer=e.getAnswer();
		div=e.getDiv();
		
		
	}
	
}
