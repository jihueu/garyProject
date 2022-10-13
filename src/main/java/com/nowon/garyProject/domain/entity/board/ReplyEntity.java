package com.nowon.garyProject.domain.entity.board;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ReplyEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)//
	@Id
	private long rno;
	@Column(nullable = false)
	private String replier;
	@Column(nullable = false)
	private String text;
	@CreationTimestamp
	private LocalDateTime createdDate;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	@JoinColumn(name = "bno")//name안하면 필드명_Pk컬럼명(연결)로 만들어진다.이건 메퍼에 사용된다. 
	@ManyToOne
	private JpaBoardEntity jpaBoardEntity;

	//private long bno;//게시글의 pk----fk
	/*
	 * @ManyToOne JpaBoardEntity board;
	 */
	

}
