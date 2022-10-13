package com.nowon.garyProject.domain.entity.board;

import javax.persistence.Column;
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
public class FileEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long fno;
	@Column(nullable = false)
	private String url;
	@Column(nullable = false)
	private String orgName;
	@Column(nullable = false)
	private String newName;
	@Column(nullable = false)
	private long size;
	
	/*
	 * @JoinColumn(name = "no", nullable = false)
	 * 
	 * @ManyToOne private JpaBoardEntity jpaBoard;
	 */
	
}
