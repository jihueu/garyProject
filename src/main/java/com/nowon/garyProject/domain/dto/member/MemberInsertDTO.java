package com.nowon.garyProject.domain.dto.member;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.nowon.garyProject.domain.entity.Member;

import lombok.Setter;

@Setter
public class MemberInsertDTO {

	private String email;
	private String name;
	private String pass;
	private String userIp; //매핑요소는 아님
	
	public MemberInsertDTO passEncode(PasswordEncoder pe) {
		this.pass=pe.encode(pass);
		return this;
	}
	
	//입력받은 dto-> Member 엔터티에 매핑
	public Member toMember() {
		//Member 객체 생성
		return Member.builder()
					.email(email)
					.name(name)
					.pass(pass)
					.userIp(userIp)
					.build();
	}
	
}
