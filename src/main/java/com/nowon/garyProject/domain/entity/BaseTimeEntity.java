package com.nowon.garyProject.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;

//독립적인 테이블생성되지 않고 자식클래스에 상속을 하기위해 사용
//주의사항 : 엔터티는 아니다 즉 where절로 사용하면 안된다. findBy()메서드 못만든다.
@Getter
@MappedSuperclass //매핑해주는 슈퍼클레스다. 엔터티로 매핑
public abstract class BaseTimeEntity {
	
	@CreationTimestamp//timestamp는 이전 시간 기록이 남아있다. 이건 구글링
	@Column(columnDefinition = "timestamp")
	private LocalDateTime createdDate;//가입일
	@UpdateTimestamp
	@Column(columnDefinition = "timestamp")
	private LocalDateTime updatedDate;//수정일
	
}
