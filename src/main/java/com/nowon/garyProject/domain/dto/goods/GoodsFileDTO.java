package com.nowon.garyProject.domain.dto.goods;

import com.nowon.garyProject.domain.entity.item.GoodsFile;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GoodsFileDTO {
	
	private long no;
	private String url;
	private String orgName;
	private String newName;
	private boolean isDefImg;
	private long size;
	
	public GoodsFileDTO(GoodsFile e) {
		super();
		this.no = e.getNo();
		this.url = e.getUrl();
		this.orgName = e.getOrgName();
		this.newName = e.getNewName();
		this.isDefImg = e.isDefImg();
		this.size = e.getSize();
	}
}
