package com.nowon.garyProject.security;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum VisualRole {
	
	VLIST("ROLE_VLIST", "비쥬얼리스트"),
	ELIST("ROLE_ELIST","전시회리스트");
	
	public final String roleName;
	public final String title;
	
	

}
