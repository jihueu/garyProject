package com.nowon.garyProject.domain.entity.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsFileRepository extends JpaRepository<GoodsFile, Long> {

}
