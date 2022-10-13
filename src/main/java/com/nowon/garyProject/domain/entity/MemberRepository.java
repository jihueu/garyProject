package com.nowon.garyProject.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	//select resultType은 매핑엔티티로 설정한다.(Member)
	Optional<Member> findByEmail(String username);
	//회원탈퇴여부도 같이 확인
	Optional<Member> findByEmailAndIsDelete(String username, boolean isDelete);
	Optional<Member> findByEmailAndIsDeleteAndIsSocial(String username, boolean isDelete, boolean isSocial);
}
