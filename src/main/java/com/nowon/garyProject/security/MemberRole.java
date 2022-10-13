package com.nowon.garyProject.security;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberRole {
	
	USER("ROLE_USER","회원"),
	ADMIN("ROLE_ADMIN","관리자");
	
	public final String roleName;
	public final String title;

}
