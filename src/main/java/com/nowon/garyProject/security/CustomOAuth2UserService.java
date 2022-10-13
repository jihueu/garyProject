package com.nowon.garyProject.security;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.nowon.garyProject.domain.entity.Member;
import com.nowon.garyProject.domain.entity.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User= super.loadUser(userRequest);
		//소셜로그인 인증완료상태...
		String  registrationId=userRequest.getClientRegistration().getRegistrationId();
		System.out.println("registrationId : "+registrationId);
		//소셜정보를 memberDB저장하는경우
		return saveSocialUser(oAuth2User, registrationId);
	}

	
	private OAuth2User saveSocialUser(OAuth2User oAuth2User, String registrationId) {
		/*
		Map<String ,Object> userInfo=oAuth2User.getAttributes();
		for(String key: userInfo.keySet()) {
			System.out.println(key+":"+userInfo.get(key));
		}
		//*/
		
		/*
		userInfo.keySet().forEach(key->{
			System.out.println(key+":"+userInfo.get(key));
		});
		*/
		String name=null;
		String email=null;
		String pass=registrationId;//비번의미없어서 일단...임시로..
		if(registrationId.equals("google")) {
			name=oAuth2User.getAttribute("name");
			email=oAuth2User.getAttribute("email");
			//pass=oAuth2User.getAttribute("sub");
			
		}else if(registrationId.equals("naver")) {
			Map<String, Object> response=oAuth2User.getAttribute("response");
			name=(String)response.get("name");
			email=(String)response.get("email");
			//pass=(String)response.get("id");
			/*
			for(String key: response.keySet()) {
				System.out.println(key+":"+response.get(key));
			}
			*/
		}else if(registrationId.equals("kakao")) {
			Map<String, Object> kakaoAccount=oAuth2User.getAttribute("kakao_account");
			email=(String)kakaoAccount.get("email");
			@SuppressWarnings("unchecked")
			Map<String, Object> profile=(Map<String, Object>) kakaoAccount.get("profile");
			//System.out.println(profile);
			name=(String)profile.get("nickname");
			pass=registrationId;//비밀번호 의미없어서 넣은거에요.
		}
		
		Optional<Member> result=memberRepository.findByEmailAndIsDeleteAndIsSocial(email,false,true);
				//.map(CustomUserDetails::new);//소셜회원중 존재하는지 체크
				
				
		if(result.isPresent()) {
			//이미등록된회원
			Member entity=memberRepository.save(
					result.get().socialUpdate(name, passwordEncoder.encode(pass))
					); //업데이트하기위해서
			return new CustomUserDetails(entity);
		}
		
		//가입이 않된회원은 소셜정보로 회원가입
		
		Member entity=memberRepository.save(Member.builder()
				.email(email).name(name).pass(passwordEncoder.encode(pass))
				.userIp("127.0.0.1")
				.isSocial(true)
				.build().addRole(MemberRole.USER));
		
		return new CustomUserDetails(entity);
	}


}
