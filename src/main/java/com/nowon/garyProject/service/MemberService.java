package com.nowon.garyProject.service;

import javax.servlet.http.HttpServletRequest;

import com.nowon.garyProject.domain.dto.member.MemberInsertDTO;

public interface MemberService {

	String save(MemberInsertDTO dto, HttpServletRequest request);

	boolean mailCheck(String email);


}
