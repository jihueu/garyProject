package com.nowon.garyProject;

import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.nowon.garyProject.domain.dto.board.JpaBoardInserDTO;
import com.nowon.garyProject.domain.entity.Category;
import com.nowon.garyProject.domain.entity.CategoryA;
import com.nowon.garyProject.domain.entity.CategoryRepository;
import com.nowon.garyProject.domain.entity.Div;
import com.nowon.garyProject.domain.entity.FaqEntity;
import com.nowon.garyProject.domain.entity.FaqEntityRepository;
import com.nowon.garyProject.domain.entity.Member;
import com.nowon.garyProject.domain.entity.MemberRepository;
import com.nowon.garyProject.domain.entity.board.FileEntity;
import com.nowon.garyProject.domain.entity.board.JpaBoardEntity;
import com.nowon.garyProject.domain.entity.board.JpaBoardEntityRepository;
import com.nowon.garyProject.domain.entity.item.GoodsRepository;
import com.nowon.garyProject.security.MemberRole;
import com.nowon.garyProject.service.JpaBoardService;

@SpringBootTest
class garyProjectApplicationTests {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	FaqEntityRepository faqEntityRepository;
	
	@Autowired
	PasswordEncoder pe;
	
	@Autowired
	GoodsRepository goodsRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	

////jpaboard/////////////////////////
	@Autowired
	JpaBoardEntityRepository repository;
	@Test
	void 파일적용저장테스트() {
		JpaBoardEntity entity= JpaBoardEntity.builder()
				.title("파일적용게시글제목")
				.content("파일적용게시글내용")
				.members(Member.builder().memNo(1).build())
				.build(); //파일선택 안한 게시글
				
			//파일추가 
			entity.addFile(FileEntity.builder()
					.url("/upload/file/")
					.orgName("image01.jpg")
					.newName("이미지01_220627100.jpg")
					.size(1024)
					.build());
			repository.save(entity);
	}
	
	
	@Autowired
	JpaBoardService service;
	
	//@Test
	void jpa데이터저장() {
		IntStream.rangeClosed(1, 100).forEach(i->{
			JpaBoardInserDTO dto=new JpaBoardInserDTO();
			dto.setTitle("제목"+i);
			dto.setContent("내용"+i);
			//service.save(dto);
		});
	}
	
	@Test
	void 카테고리_생성() {
		CategoryA cateA=CategoryA.SPECIAL;
		long count=categoryRepository.countByCateA(cateA);
		
		//System.out.println("count : "+count);
		
		//*
		categoryRepository.save(Category.builder()
				.name("호안미로")
				.code(++count)
				.cateA(cateA)
				.build().createCaNo());
				//*/
	}
	
	
	
	
	
	//@Test
	void faq테스트() {
		
			
		
		IntStream.rangeClosed(1, 10).forEach(i->{
			
			Div[] divs=Div.values();
			for(int x=0;x<divs.length;x++) {
				faqEntityRepository.save(FaqEntity.builder()
						.question(divs[x].name()+"Q"+i)
						.answer(divs[x].name()+"A"+i)
						.div(divs[x])
						.build());}
			/*
			 * Div div; div=Div.TICKET; //div.values();
			 * 
			 * faqEntityRepository.save(FaqEntity.builder()
			 * .question(div.name()+"질문"+i).answer(div.name()+"답변"+i).div(div) .build());
			 */
		});
	}
	
	
	
	//@Test
	void 관리자생성() {
		memberRepository.save(Member.builder()
									.email("admin")
									.pass(pe.encode("1111"))
									.name("이지희")
									.userIp("127.0.0.1")
									.build()
									.addRole(MemberRole.ADMIN)
									.addRole(MemberRole.USER));
	}
	//@Test
	void 회원생성() {
		memberRepository.save(Member.builder()
				.email("test01")
				.pass(pe.encode("1111"))
				.name("테스트유저")
				.userIp("127.0.0.2")
				.build()
				.addRole(MemberRole.USER));
	}
	
	//@Test
	void 회원가입테스트() {
	Member entity=Member.builder()
						.email("email1")
						.pass("1111")
						.name("가리")
						.userIp("127.0.0.0")
						.build();
	memberRepository.save(entity);
	
	}
	//@Test
	void 회원정보수정테스트() {
		Member entity=Member.builder()
				.memNo(1)
				.email("email")
				.pass("2222")
				.name("가리")
				.userIp("127.0.0.0")
				.build();
		memberRepository.save(entity);
	}
	@Rollback(value = false)
	@Transactional // 설정해주면 save 없이 업데이트 가능
	//@Test
	void 회원정보수정테스트2() {
		/*
		 * Member entity=memberRepository.findById(1L).orElseThrow() .name("세이브2");
		 */
		System.out.println("----------------");
		//@DynamicUpdate를 엔터티에 설정해놓으면 쿼리가 딱 변경사항만 날라간다.
		//Hibernate: update member set name=?, pass=?, updated_date=? where mem_no=?
		//entity.updatePass("3333").name("이가리");//Member엔티티에 updatePass메서드를 만들어줌
		//memberRepository.save(entity);
	}
	

}
