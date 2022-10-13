package com.nowon.garyProject.domain.dto.visual;

import com.nowon.garyProject.domain.dto.FileData;
import com.nowon.garyProject.domain.entity.VisualFile;

import lombok.Setter;

@Setter
public class VisualInsertDTO extends FileData{
	
	private String mtitle;
	private String stitle;
	private String link;
	private int price;
	
	
	public VisualFile toVisuslFile() {
		
		return VisualFile.builder()
				.mtitle(mtitle).stitle(stitle).link(link).price(price)
				.url(url).orgName(orgName).size(size)
				.build();
	}


	public void addFileData(FileData fileData) {
		url=fileData.getUrl();
		orgName=fileData.getOrgName();
		size=fileData.getSize();
		
	}
	
	

}
