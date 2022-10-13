package com.nowon.garyProject.domain.entity.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	//프로그래머가 쿼리메서드 생성가능 : 규칙에 맞게 생성
}
