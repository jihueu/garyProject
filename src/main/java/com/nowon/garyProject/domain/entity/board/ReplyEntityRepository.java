package com.nowon.garyProject.domain.entity.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyEntityRepository extends JpaRepository<ReplyEntity, Long> {
	//<>제네릭에 엔터티가 들어가는거요
	List<ReplyEntity> findAllByJpaBoardEntityNo(long bno);


	

	
}
