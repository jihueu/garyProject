package com.nowon.garyProject.service.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nowon.garyProject.domain.dto.member.MemberInsertDTO;
import com.nowon.garyProject.domain.entity.Member;
import com.nowon.garyProject.domain.entity.MemberRepository;
import com.nowon.garyProject.security.MemberRole;
import com.nowon.garyProject.service.MemberService;
import com.nowon.garyProject.utils.ClientIP;

@Service
public class MemberServiceProcess implements MemberService {
	
	//DAO: jpa-repository, mybatis-mapper, jdbc-Connection(singleton으로구성)
	
	@Autowired
	private MemberRepository repository;
	
	@Autowired
	PasswordEncoder pe;

	@Override
	public String save(MemberInsertDTO dto, HttpServletRequest request) {
		dto.passEncode(pe);
		//프록시서버(카페24) 또는 로드 밸런스를 통해 서버에 접속한 경우 getRemoteAddr()로 요청안됨
		//그래서 ClientIP클래스를 만듦(선생님소스제공)
		//dto.setUserIp(request.getRemoteAddr());
		dto.setUserIp(ClientIP.getClientIP(request));
		
		repository.save(dto.toMember().addRole(MemberRole.USER));
		
		return "redirect:/loginPage";
	}

	@Override
	public boolean mailCheck(String email) {
		
		Optional<Member>result= repository.findByEmail(email);
		
		if(result.isEmpty()) return false;//DB에 존재하지않으면 사용가능의 false
		return true;//이미 존재한경우 tur
	}

	

}
