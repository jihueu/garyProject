package com.nowon.garyProject.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.nowon.garyProject.domain.dto.visual.VisualUpdateDTO;
import com.nowon.garyProject.security.VisualRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class VisualFile {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long vno;
	private String link;//이미지 클릭하면 이동하는 페이지
	private String url;
	private String orgName;
	private long size;
	private String mtitle;
	private String stitle;
	private int price;
	private boolean isShow;
	
	@Builder.Default
	@Enumerated(EnumType.STRING)//DB에 저장시 문자열 저장할때 적용
	@ElementCollection(fetch = FetchType.EAGER)
	Set<VisualRole> visualRole=new HashSet<>();
	
	public VisualFile addRole(VisualRole role) {
		visualRole.add(role);
		return this;
	}
		
	public VisualFile updateIsShow(boolean isShow) {
		this.isShow=isShow;
		return this;
	}
	public VisualFile updateMtitle(String mtitle) {
		this.mtitle=mtitle;
		return this;
	}


	public VisualFile updateStitle(String stitle) {
		this.stitle=stitle;
		return this;
	}


	public VisualFile updateData(VisualUpdateDTO dto) {
		
		this.link=dto.getLink();
		this.mtitle=dto.getMtitle();
		this.stitle=dto.getStitle();
		return this;
	}

}
