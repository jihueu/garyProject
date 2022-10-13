package com.nowon.garyProject.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nowon.garyProject.domain.entity.Member;
import com.nowon.garyProject.domain.entity.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
	//
	
	//DAO : jpa-repository
	//@Autowired	
	private final MemberRepository repository;
	
	 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// username->로그인페이지에서 입력한 email 
		System.out.println(username);
		//db에 회원존재하는지 체크 존재하면 UserDetails 으로 세팅해서 리턴한다. 
		//isDeleted false: 정상회원, True : 탈퇴회원  *휴면회원같은 경우 인증핸들러에서 구현 : role도 하나 추가
		Optional<Member> result = repository.findByEmailAndIsDeleteAndIsSocial(username, false, false);
		
		if(result.isEmpty()) {
			throw new UsernameNotFoundException("존재하지 않거나 탈퇴회원");
		}
		//인증정보를 저장하는 객체를 만들거다. User를 상속시킬거임
		
		//UserDetails타입으로 리턴 : User를 활용
		//너무 빈약함 그래서 DTO 만들거임
		System.out.println(">>>>>>Role read");
		return new CustomUserDetails(result.get());
	}

}
