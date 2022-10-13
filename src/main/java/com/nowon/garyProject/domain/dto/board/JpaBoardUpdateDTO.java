package com.nowon.garyProject.domain.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class JpaBoardUpdateDTO {
	//private int no;
	private String title;
	private String content;
}
