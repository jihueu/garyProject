package com.nowon.garyProject.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.nowon.garyProject.domain.entity.item.Goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Category {
	
	@Id
	private long caNo;
	
	public Category createCaNo() {
		caNo=cateA.code +code;
		return this;
	}

	private String name;
	private long code;
	
	@Enumerated(EnumType.STRING)
	private CategoryA cateA;//상위카테그리
	
	@Builder.Default	
	@ManyToMany(mappedBy = "categorys")
	Set<Goods> goods=new HashSet<>();
	
}
