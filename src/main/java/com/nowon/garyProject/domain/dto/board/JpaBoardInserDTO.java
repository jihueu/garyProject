package com.nowon.garyProject.domain.dto.board;

import com.nowon.garyProject.domain.entity.Member;
import com.nowon.garyProject.domain.entity.board.JpaBoardEntity;

import lombok.Data;
import lombok.ToString;
@ToString
@Data
public class JpaBoardInserDTO {
	
	private String title;
	private String content;
	private String newName;//이미업로드된 새로운 파일이름
	private int memNo;
	//field data는 DB에 저장할 목적으로 만든것
	//DB는 Entity로만 저장해야한다.
	//DTO->Entity로 매핑 필요
	//메서드를 만들어서 처리하면 편합니다.
	
	public JpaBoardEntity toEntity() {
		//빌더설정하고 entity에 클래스명으로 직접 접근한다.
		return JpaBoardEntity.builder()
				.title(title).content(content).members(Member.builder().memNo(memNo).build())
				.build();
	}

}
