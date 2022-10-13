package com.nowon.garyProject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowon.garyProject.domain.dto.member.MemberInsertDTO;
import com.nowon.garyProject.service.MailService;
import com.nowon.garyProject.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService service;
	
	private final MailService mailService;
	
	@PostMapping("/common/signup")
	public String signup(MemberInsertDTO dto, HttpServletRequest request) {
		return service.save(dto, request);
		//HttpServletRequest request IP주소 받기위해사용
	}
	

	@ResponseBody
	@PostMapping("/request-key/mail")
	public long requestMailKey(String email) {
		System.out.println(email);
		return mailService.mailSend(email);
	}
	@ResponseBody
	@GetMapping("/request-key/getkey")
	public String mailSend(HttpSession session) {
		System.out.println("마지막접속시간 : "+ session.getLastAccessedTime());
		System.out.println("생성접속시간 : "+ session.getCreationTime());
		System.out.println("유지시간 : "+ session.getMaxInactiveInterval());
		
		//session에 저장된 code=mailKey
		return (String)session.getAttribute("mailKey");
	}
	
	@ResponseBody
	@GetMapping("/request-key/remove")
	public void requestRemove(HttpSession session) {
		session.removeAttribute("mailKey");
	}
	
	@ResponseBody
	@PostMapping("/common/mailauth")
	public boolean mailCheck(String email) {
		System.out.println("email : "+email);
		return service.mailCheck(email);
		//return service.findByEmail();
	}

}
