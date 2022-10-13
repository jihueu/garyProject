package com.nowon.garyProject.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import com.nowon.garyProject.security.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
//@DynamicInsert // insert 시 entity에서 null쿼리를 만들어 내지 않음
@DynamicUpdate //update하는 컬럼만 쿼리에 적용되게해준다.
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Member extends BaseTimeEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long memNo;
	
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String pass;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String userIp;
	
	private boolean isDelete;//default:false(0) 탈퇴여부 DB에는 0,1이 들어감
	private boolean isSocial;//default:false(0) 소셜로그인 가입자
	
	@Builder.Default
	@Enumerated(EnumType.STRING)//DB에 저장시 문자열 저장할때 적용
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<MemberRole> roleSet=new HashSet<>();
	
	public Member addRole(MemberRole role) {
		roleSet.add(role);
		return this;
	}
	public Member removeRole(MemberRole role) {
		roleSet.remove(role);
		return this;
	}
	public Member socialUpdate(String name,String pass) {
		this.name=name;
		this.pass=pass;
		return this;
	}

}
