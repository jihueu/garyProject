package com.nowon.garyProject.domain.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	long countByCateA(CategoryA cateA);

	List<Category> findByCaNoBetween(long caNo, long l);

	


}
