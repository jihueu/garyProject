package com.nowon.garyProject.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CategoryA {
	
	ARTIST("작가",1100,"artist"),
	HANDMADE("수제품",1200,"handmade"),
	GIFT("선물용",1300,"pillow"),
	MATERIAL("재료",1400,"material"),
	SEASON("시즌상품",1900,"season"),
	SPECIAL("기획상품",2000,"special");
	
	final String koName;
	final long code;
	final String lower;

}
