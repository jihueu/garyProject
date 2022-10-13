package com.nowon.garyProject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.garyProject.domain.dto.board.JpaBoardInserDTO;
import com.nowon.garyProject.domain.dto.board.JpaBoardUpdateDTO;
import com.nowon.garyProject.domain.dto.board.ReplyInsertDTO;
import com.nowon.garyProject.domain.entity.board.FileEntity;
import com.nowon.garyProject.domain.entity.board.FileRepository;
import com.nowon.garyProject.service.JpaBoardService;

@Controller
public class JpaBoardController {
	
	@Autowired
	private JpaBoardService service;
	
	@GetMapping("/board")
	public String list(Model model,@RequestParam(defaultValue = "1") int pageNo) {
		service.list(pageNo, model);
		//페이지이동
		return "board/list";
	}
	
	@GetMapping("/jpaboard/write")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/jpaboard/write")
	public String write(MultipartFile[] file, JpaBoardInserDTO dto) {
		//System.out.println(dto);
		service.save(file, dto);
		return "redirect:/jpaboard";
	}
	@GetMapping("/jpaboard/{no}") //{no}변수 표현식
	public String pageNo(@PathVariable long no, Model model) {
		service.detail(no, model);
		return "board/detail";
	}
	
	@PutMapping("/jpaboard/{no}")
	public String update(@PathVariable long no, JpaBoardUpdateDTO dto) {
		service.update(dto, no);
		return "redirect:/jpaboard/"+no;
	}
	@ResponseBody
	@DeleteMapping("/jpaboard/{no}")
	public void delete(@PathVariable long no) {
		service.delete(no);
	}
	@ResponseBody
	@PostMapping("/jpaboard/{bno}/reply")
	public boolean reply(ReplyInsertDTO dto) {
		//댓글여부 확인을 위해 boolean으로 리턴을 받아보겠어
		return service.reply(dto);
		
	}
	//@ResponseBody ajax를 불러오면 리턴정보를 문자열 그대로 받아버리기 때문에 경로로 인식하지 못한다!!!!!!
	//상세페이지에서 페이지로딩 후 댓글 읽어오기
	@GetMapping("/jpaboard/{bno}/replies")
	public String replies(@PathVariable long bno,Model model) {
		return service.replies(bno,model);
	}
	
	
	@ResponseBody //문자열 리턴->성공시 success: function(result)
	@PostMapping("/jpaboard/fileupload")
	public String fileupload(MultipartFile file, String prevImgName) {
		return service.fileUpload(file, prevImgName);
	}
	
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private ResourceLoader resourceLoader;
	//
	@GetMapping(value = "/jpaboard/files/{fno}", produces =MediaType.APPLICATION_OCTET_STREAM_VALUE )
	public ResponseEntity<Resource> downloadFile(@PathVariable  long fno, @RequestHeader("user-agent")String userAgent) throws Exception {
		//ResponseEntity<Resource> 이거 대신 byte배열형식으로 사용해도된다.(OCTET가 8bit=1byte)
		System.out.println(userAgent);
		if(userAgent.contains("Edg")) {
			System.out.println("엣지 브라우저에서 실행");
		}else {
			System.out.println("크롬 부라우저에서 실행");
		}
		
		
		FileEntity entity= fileRepository.findById(fno).orElseThrow();

		Resource resource=resourceLoader.getResource("classpath:static/fileupload/"+entity.getNewName());
				
		String fileName= new String(entity.getOrgName().getBytes("UTF-8"),"ISO-8859-1"); //엣지와 인터넷익스플로어는
		 		  
		return ResponseEntity.ok()//상태코드 설정 200(정상)
		  .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename"+fileName)
		  .header(HttpHeaders.CONTENT_LENGTH, entity.getSize()+"") 
		  .body(resource);
		 
		
		
		/*
		 * String downloadName=URLEncoder.encode(entity.getOrgName(), "utf-8");
		 * HttpHeaders headers=new HttpHeaders();
		 * headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+
		 * downloadName);
		 * 
		 * return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
		 */

		 
	}
	
	
}
