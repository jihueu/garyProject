package com.nowon.garyProject.domain.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nowon.garyProject.security.VisualRole;



@Repository
public interface VisualFileRepository extends JpaRepository<VisualFile, Long> {


	List<VisualFile> findAllByVisualRole(VisualRole vRole);

	List<VisualFile> findAllByIsShowAndVisualRole(boolean isShow, VisualRole vlist);


	
}
