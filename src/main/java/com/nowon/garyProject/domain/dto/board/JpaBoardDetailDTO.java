package com.nowon.garyProject.domain.dto.board;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.nowon.garyProject.domain.entity.board.JpaBoardEntity;

import lombok.Getter;


@Getter
public class JpaBoardDetailDTO {
	
	private long no;
	private String title;
	private String content;
	private int readCount;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	private String email;//member에 email
	
	//private List<ReplyEntityListDTO> replies; 
	
	//첨부파일정보 읽어오기
	private List<FileDTO> files;
	
	
 	
	public JpaBoardDetailDTO(JpaBoardEntity e) {
	
		this.no = e.getNo();
		this.title = e.getTitle();
		this.content = e.getContent();
		this.readCount = e.getReadCount();
		this.createdDate = e.getCreatedDate();
		this.updatedDate = e.getUpdatedDate();
		//fetchType.LAZY일때 e.getFiles() 실행되는 시점에서 파일에 대한 쿼리가 실행됩니다.
		System.out.println("파일엔터티가 실행되요");
		this.files=e.getFiles().stream().map(FileDTO::new).collect(Collectors.toList());
		
		this.email=e.getMembers().getEmail();
//		replies=e.getReplies().stream()
//				.map(ReplyEntityListDTO::new).collect(Collectors.toList());

	}
}
