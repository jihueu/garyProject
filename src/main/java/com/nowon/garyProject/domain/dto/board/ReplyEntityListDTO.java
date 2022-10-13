package com.nowon.garyProject.domain.dto.board;

import java.time.LocalDateTime;

import com.nowon.garyProject.domain.entity.board.ReplyEntity;

import lombok.Data;

@Data
public class ReplyEntityListDTO {
	
	private long rno;
	private String text;
	private String replier;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	public ReplyEntityListDTO(ReplyEntity e) {
		this.rno=e.getRno();
		this.text=e.getText();
		this.replier=e.getReplier();
		this.createdDate=e.getCreatedDate();
		this.updatedDate=e.getUpdatedDate();
	}

}
