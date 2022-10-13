package com.nowon.garyProject.domain.entity.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderGoodsRepository extends JpaRepository<OrderGoods, Long> {

	List<OrderGoods> findAllByMembersMemNo(int memNo);

	

}
