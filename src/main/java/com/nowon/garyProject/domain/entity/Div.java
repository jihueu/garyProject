package com.nowon.garyProject.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Div {
	
	USE("사이트 이용","uses"),
	MEMBER("회원","members"),
	MEMBERSHIP("멤버십","memberships"),
	ONLINE("온라인","onlines"),
	BENEFIT("혜택","benefit"),
	TICKET("권람권","tickets"),
	STORE("스토어","stores"),
	SPECIAL("스페셜관","specials");
	
	final String title;
	final String url;
	
	

}
