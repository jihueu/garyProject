package com.nowon.garyProject.domain.entity.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {


	List<Goods> findAllByCategorysCaNoBetween(long caNo, long l);

}
