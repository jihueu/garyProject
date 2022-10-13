package com.nowon.garyProject.service.impl;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.garyProject.domain.dto.board.JpaBoardDetailDTO;
import com.nowon.garyProject.domain.dto.board.JpaBoardInserDTO;
import com.nowon.garyProject.domain.dto.board.JpaBoardListDTO;
import com.nowon.garyProject.domain.dto.board.JpaBoardUpdateDTO;
import com.nowon.garyProject.domain.dto.board.ReplyEntityListDTO;
import com.nowon.garyProject.domain.dto.board.ReplyInsertDTO;
import com.nowon.garyProject.domain.entity.board.FileEntity;
import com.nowon.garyProject.domain.entity.board.JpaBoardEntity;
import com.nowon.garyProject.domain.entity.board.JpaBoardEntityRepository;
import com.nowon.garyProject.domain.entity.board.ReplyEntityRepository;
import com.nowon.garyProject.service.JpaBoardService;
import com.nowon.garyProject.utils.PageInfo;

@Service
public class JpaBoardServiceproc implements JpaBoardService {
	//JPA DAO: repository객체가 수행 
	@Autowired
	private JpaBoardEntityRepository repository;
	
	@Override
	public void save(MultipartFile[] file, JpaBoardInserDTO dto){
		
		JpaBoardEntity entity= dto.toEntity();
		
		//저장은 지정된 entity만 가능합니다.
		for(MultipartFile f:file) {
			//!isEmpty()=false 일때
			if(!f.isEmpty()) {//파일이 존재하면
				//파일 업로드&파일테이블에 정보저장
				String orgName=f.getOriginalFilename();
				String[] strs = orgName.split("[.]");//"."이면안됨 [.]감싸주고 입력 .특별케이스
				String extension="."+strs[strs.length-1];//확장자 저장
				//String newName=System.nanoTime()+extension;
				UUID uuid=UUID.randomUUID();
				String newName=uuid+extension;
				String newName2=uuid.toString()+"_"+orgName;
				
				
				  System.out.println("오리지날네임 : "+orgName);
				  System.out.println("uuid네임 : "+newName);
				  System.out.println("uuid네임2 : "+newName2);
				 
				
				String url="/fileupload/";
				//파일업로드 MultipartFile에서 지원해줌
				//클래스패스를 사용하여 접근해볼겁니다.-> 바이너리리소스자원에 접근 : bin폴더에 생성됨 확인 src에는 없음, 웹에는 바로반영됨 
				//주의사항 : clean or refresh하면 빈폴더에서 삭제됨 (src에 없으니까 수동으로 저장해야함)
				ClassPathResource cprTemp=new ClassPathResource("static"+url+"temp");
				//물리경로 : 새로고침하면 src에 생성됨 웹에서 확인안됨 -> 새로고침하면 bin에 생김 웹서버는 bin과 연계되어있는것 숙지
				//String filePath="C:/java/spring/today_project/src/main/resources/static/fileupload";
				//리눅스 경우 : String filePath="/home/ec2-user/src/root/"; 웹서버경로와 디렉토리경로가 같다.
				
				try {
					File newFile=new File(cprTemp.getFile(), dto.getNewName());
					ClassPathResource uploadDir=new ClassPathResource("static"+url);
					File dir=new File(uploadDir.getFile(), dto.getNewName());
					newFile.renameTo(dir);
					System.out.println("temp->fileupload로 이동");
					
					entity.addFile(FileEntity.builder()
							.url(url)
							.size(f.getSize())
							.orgName(f.getOriginalFilename())
							.newName(dto.getNewName())
							.build());
				} catch (IllegalStateException |IOException e) {
					e.printStackTrace();
				} 
			}
		}
			
		/*	
		System.out.println("확인 좀 해볼게요"+f.isEmpty());
		System.out.println("확인 좀 해볼게요"+f.getName());//파라미터이름
		System.out.println("확인 좀 해볼게요"+f.getOriginalFilename());//파일이름
		System.out.println("확인 좀 해볼게요"+f.getContentType());//파일형식
		System.out.println("확인 좀 해볼게요"+f.getSize());//bytes
		System.out.println("-------------------------------------------");
		}
		System.out.println("확인 좀 해볼게요 dto:"+dto);*/
		repository.save(entity);
		
	}

	@Override
	public void list(int pageNo, Model model) {
		/*List<JpaBoardEntity> r=repository.findAll();
		List<JpaBoardListDTO> list=new Vector<JpaBoardEntity>();
		for(JpaBoardEntity e : r) {
			JpaBoardListDTO dto=new JpaBoardListDTO(e);
			list.add(dto);
		}
		model.addAttribute("list",list);
		*/
		//연관관계(join)에서 지연 로딩or로딩을 못할 수 있다.
		//서버에서 페이지(view)로 넘길때는 entity말고 DTO를 이용하자
		/*List<JpaBoardListDTO> result=repository.findAll()//List<JpaBoardEntity>
												.stream()//Stream<JpaBoardEntity>
												.map(JpaBoardListDTO::new)//.map(e->new JpaBoardListDTO(e))
												.collect(Collectors.toList());*/
		
		int page=pageNo-1;
		int size=10;
		Sort sort =Sort.by(Direction.DESC, "no");
		Pageable pageable = PageRequest.of(page, size, sort);
		
		//repository.findAll(pageable).stream().map(JpaBoardListDTO::new).collect(Collectors.toList());
		
		Page<JpaBoardEntity> pageObj = repository.findAll(pageable);
		int pageTotal=pageObj.getTotalPages();
		PageInfo pageInfo=PageInfo.getInstance(pageNo, pageTotal);
		model.addAttribute("pi", pageInfo);
		model.addAttribute("list", pageObj.getContent().stream().map(JpaBoardListDTO::new).collect(Collectors.toList()));
	}
	@Transactional//파일정보를 읽기위해서 추가 (현재 지연로딩이니까)
	@Override
	public void detail(long no, Model model) {
		
		/* 그 옛날 람다식이 없을때
		 * Optional<JpaBoardDetailDTO> result = repository.findById(no) .map(new
		 * Function<JpaBoardEntity, JpaBoardDetailDTO>() {
		 * 
		 * @Override public JpaBoardDetailDTO apply(JpaBoardEntity t) { // TODO
		 * Auto-generated method stub return null; } });
		 */
		
		/*
		 * Optional<JpaBoardDetailDTO> result = repository.findById(no)
		 * .map(JpaBoardDetailDTO::new);
		 */
		/*
		 * JpaBoardEntity entity=repository.findById(no).orElseThrow();
		 * JpaBoardDetailDTO dto=new JpaBoardDetailDTO(entity);
		 * model.addAttribute("detail", dto);
		 */
		/*
		 * JpaBoardEntity entity=repository.findById(no).orElseThrow();
		 * System.out.println("db->entity"); model.addAttribute("detail", entity);
		 */
		
		model.addAttribute("detail", repository.findById(no).map(JpaBoardDetailDTO::new).get());

	}

	//게시글 수정처리 @Transactional 적용안했을때
//	@Override
//	public void update(JpaBoardUpdateDTO dto, long no) {
//		//1.수정할 대상 찾기
//		JpaBoardEntity entity=repository.findById(no).orElseThrow();
//		System.out.println("수정대상:"+entity);
//		//2.수정할 데이터만 변경
//		entity.update(dto);
//		System.out.println("수정완료"+entity);
//		//3.수정된 데이터를 저장
//		repository.save(entity);
//	}
	@Transactional
	@Override
	public void update(JpaBoardUpdateDTO dto, long no) {
//		repository.findById(no).map(e->{return e.update(dto);});
		repository.findById(no).map(e->e.update(dto));
	}

	@Override
	public void delete(long no) {
		repository.deleteById(no);
		
	}
	@Override
	public boolean reply(ReplyInsertDTO dto) {
		//댓글저장할려면?
		//게시글이 있어야 댓글을 쓸수 있어요
		replyEntityRepository.save(dto.toEntity());
		return true;
	}
	
	@Autowired
	private ReplyEntityRepository replyEntityRepository;

	//댓글 로딩
	@Override
	public String replies(long bno, Model model) {
		List<ReplyEntityListDTO> result = replyEntityRepository.findAllByJpaBoardEntityNo(bno)
				.stream().map(ReplyEntityListDTO::new).collect(Collectors.toList());
		model.addAttribute("list", result);
		return "view/jpaboard/reply/list";
	}

	@Override
	public String fileUpload(MultipartFile file,String prevImgName) {
		 	String orgName=file.getOriginalFilename();
		 	UUID uuid = UUID.randomUUID();
		 	String newName=uuid.toString()+orgName;
		 	ClassPathResource cpr=new ClassPathResource("static"+"/fileupload/temp");
			try {
				File location=cpr.getFile();//temp폴더
				File preImg=new File(location, prevImgName);
				if(preImg.isFile())preImg.delete();
				
//				File[] files=location.listFiles();
//				for(File f : files) {f.delete();}
				
				file.transferTo(new File(location, newName));
			} catch (IllegalStateException | IOException e) {
				
				e.printStackTrace();
			} 
		return newName;
	}
	
	

	
	
	
}


