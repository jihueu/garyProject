package com.nowon.garyProject.domain.dto.visual;

import com.nowon.garyProject.domain.dto.FileData;
import com.nowon.garyProject.domain.entity.VisualFile;

import lombok.Getter;

@Getter
public class VisualListDTO extends FileData{
	
	private long vno;
	private String link;
	private String url;
	private String orgName;
	private long size;
	private String mtitle;
	private String stitle;
	private int price;
	private boolean isShow;
	
	public VisualListDTO(VisualFile e) {
		this.vno = e.getVno();
		this.url = e.getUrl();
		this.link = e.getLink();
		this.orgName = e.getOrgName();
		this.size = e.getSize();
		this.mtitle = e.getMtitle();
		this.stitle = e.getStitle();
		this.price=e.getPrice();
		this.isShow=e.isShow();
	}
	
	
	

}
