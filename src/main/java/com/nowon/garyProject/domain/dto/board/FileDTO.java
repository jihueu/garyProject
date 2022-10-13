package com.nowon.garyProject.domain.dto.board;

import com.nowon.garyProject.domain.entity.board.FileEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class FileDTO {
	
	private long fno;
	private String url;
	private String orgName;
	private String newName;
	private long size;
	
	//간결하게 람다식으로 매핑해주기우해 만듦
	public FileDTO(FileEntity e) {
		
		this.fno = e.getFno();
		this.url = e.getUrl();
		this.orgName = e.getOrgName();
		this.newName = e.getNewName();
		this.size = e.getSize();
	}
	
	
}
