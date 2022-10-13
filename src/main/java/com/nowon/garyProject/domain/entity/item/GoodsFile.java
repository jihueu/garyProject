package com.nowon.garyProject.domain.entity.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class GoodsFile {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long no;
	private String url;
	private String orgName;
	private String newName;
	private boolean isDefImg;
	private long size;
	

}
