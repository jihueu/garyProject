package com.nowon.garyProject.domain.dto.board;

import com.nowon.garyProject.domain.entity.board.JpaBoardEntity;
import com.nowon.garyProject.domain.entity.board.ReplyEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyInsertDTO {
	private long bno;
	private String replier;
	private String text;
	
	public ReplyEntity toEntity() {
		return ReplyEntity.builder()
				.replier(replier)
				.text(text)
				.jpaBoardEntity(JpaBoardEntity.builder().no(bno).build())
				.build();
	}
}
