package com.nowon.garyProject.domain.dto.goods;

import com.nowon.garyProject.domain.entity.Category;

import lombok.Getter;


@Getter
public class CategoryDto {
	
	private long caNo;
	private String name;
	private long code;
	public CategoryDto(Category e) {
		
		this.caNo = e.getCaNo();
		this.name = e.getName();
		this.code = e.getCode();
	}
	
	
	
	
}
