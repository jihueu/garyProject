package com.nowon.garyProject.domain.entity.board;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.nowon.garyProject.domain.dto.board.JpaBoardUpdateDTO;
import com.nowon.garyProject.domain.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
//entity에는 애초에 setter하지말자 잘못하면 데이타 다 난리난다.
//entity는 @ToString을 잘 써야함 함부로 쓰면 안됨 연간테이블이 있을때 무한루프걸릴수 있음
//그리고 @Data도 지양한다.

@Builder
@AllArgsConstructor
@NoArgsConstructor // DB에서 결과를 매핑하려면 필요
@Getter
@Entity //기본적으로 DB하고 매핑되는 클래스
public class JpaBoardEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
	@Id //PK컬럼
	private long no;
	
	@Column(nullable = false)//not null
	private String title;
	@Column(columnDefinition = "text not null") //mysql/mariadb에서 text:max size limit 없다.=오라클에서 CLOB
	private String content;
	private  int readCount;
	//jpa기능 활용 : TodayProjectApplication클래스에 @EnableJpaAuditing적용해야한다.
	@CreationTimestamp
	private LocalDateTime createdDate;
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	//댓글 연계, FK만들어진다. 자바에는 없다. many쪽 테이블에 있다.
	
	//joinColumn이 써있는곳이 주체
	@Builder.Default
	@OneToMany(mappedBy = "jpaBoardEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)//연계된 테이블내용도 삭제 
	private Collection<ReplyEntity> replies=new Vector<ReplyEntity>();
		
	//update 메서드를 만듦
	public JpaBoardEntity update(JpaBoardUpdateDTO dto) {
		this.title=dto.getTitle();
		this.content=dto.getContent();
		return this;
	}
	//단방향 설정 : FK 똑가이 N에 생기고 수정을 위해 cascadeType.ALL로 설정
	@Builder.Default
	@JoinColumn(name = "bno")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//지연로딩은 get() 실행될때 읽어진다.
	private List<FileEntity> files=new Vector<FileEntity>();
	//파일추가메서드
	
	public JpaBoardEntity addFile(FileEntity file) {
		files.add(file);
		return this;
	}
	//파일제거메서드
	public JpaBoardEntity removeFile(FileEntity file) {
		files.remove(file);
		return this;
	}
	
	@JoinColumn(name = "memNo")
	@ManyToOne
	private Member members;
	
	
	
}
