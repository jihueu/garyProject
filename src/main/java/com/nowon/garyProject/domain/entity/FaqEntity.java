package com.nowon.garyProject.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "faq")
public class FaqEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long faqNo;
	
	private String question;
	private String answer;
	
	@Column(name = "divs")
	@Enumerated(EnumType.STRING)
	private Div div;//div를 컬럼이름으로 쓸 수 없음 에러남
	

}
