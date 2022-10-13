package com.nowon.garyProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.garyProject.domain.dto.visual.VisualInsertDTO;
import com.nowon.garyProject.domain.dto.visual.VisualUpdateDTO;
import com.nowon.garyProject.service.VisualService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class VisualController {
	
	@Autowired
	private VisualService service;
	
	//등록페이지로 이동
	@GetMapping("/admin/visuals/write")
	public String page() {
		return "admin/visual/write";
	}
	//관리자페이지 등록 처리 write
	@PostMapping("/admin/visuals")
	public String save(MultipartFile vimg, VisualInsertDTO dto) {
		return service.save(vimg, dto);
	}
	//등록된 이미지 목록 : isShow 체크박스 반영
	@ResponseBody
	@PutMapping("/common/visuals/{vno}/isShow")
	public boolean updateIsShow(@PathVariable long vno, boolean isShow) {
		return service.updateIsShow(vno, isShow);
	}
	@ResponseBody
	@PutMapping("/common/visuals/{vno}")
	public boolean updateData(@PathVariable long vno, VisualUpdateDTO dto) {
		log.info("putmapping>>>>>>>");
		return service.updateData(vno, dto);
	}
	
	@ResponseBody
	@DeleteMapping("/visual/list/{no}")
	public void delete(@PathVariable long no) {
		service.delete(no);
	}
	
	//관리자페이지 등록된 이미지 list
	@GetMapping("/admin/visuals")
	public String list(Model model) {
		return service.list(model);
	}
	//ajax에서 요청한 메서드
	//동록된 이미지를 index페이지로 넘김
	@GetMapping("/common/visuals")
	public String indexList(Model model) {
		return service.indexList(model);
	}
	
	
	////admin ewite elist////////////////
	@GetMapping("/admin/ewrite")
	public String ewritePage() {
		return "admin/visual/ewrite";
	}
	@PostMapping("/admin/elist")
	public String elist(MultipartFile vimg, VisualInsertDTO dto) {
		return service.saveElist(vimg, dto);
	}
	@GetMapping("/admin/elist")
	public String elistPage(Model model) {
		return service.elist(model);
	}
	
	////////////////////////////////////
	
	@GetMapping("/common/elistpage")
	public String commonelistPage(Model model) {
		service.elistpage(model);
		return"visual/elistpage";
	}
	
	
}
