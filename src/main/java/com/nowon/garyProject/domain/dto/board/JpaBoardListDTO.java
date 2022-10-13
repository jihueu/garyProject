package com.nowon.garyProject.domain.dto.board;

import java.time.LocalDateTime;

import com.nowon.garyProject.domain.entity.board.JpaBoardEntity;

import lombok.Getter;


@Getter
public class JpaBoardListDTO {
	private long no;
	private String title;
	private int readCount;
	private LocalDateTime updatedDate;
	
	public JpaBoardListDTO(JpaBoardEntity e) {
		no=e.getNo();
		title=e.getTitle();
		readCount=e.getReadCount();
		updatedDate=e.getUpdatedDate();
		
	}


}
