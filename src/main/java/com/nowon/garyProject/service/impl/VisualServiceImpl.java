package com.nowon.garyProject.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.garyProject.domain.dto.FileData;
import com.nowon.garyProject.domain.dto.visual.VisualInsertDTO;
import com.nowon.garyProject.domain.dto.visual.VisualListDTO;
import com.nowon.garyProject.domain.dto.visual.VisualUpdateDTO;
import com.nowon.garyProject.domain.entity.VisualFile;
import com.nowon.garyProject.domain.entity.VisualFileRepository;
import com.nowon.garyProject.security.VisualRole;
import com.nowon.garyProject.service.VisualService;
import com.nowon.garyProject.utils.MyFileUtils;

@Service
public class VisualServiceImpl implements VisualService {

	@Autowired
	private VisualFileRepository repository;
	
	@Override
	public String save(MultipartFile file, VisualInsertDTO dto) {
		//파일업로드
		//db저장
		String url="/images/visual/vlist/";//업로드할 파일 위치
		FileData fileData= MyFileUtils.upLoad(file, url);
		dto.addFileData(fileData);
		repository.save(dto.toVisuslFile().addRole(VisualRole.VLIST));
		return "redirect:/admin/visuals";
		
	}
	////////index 메인 출력 이미지 list들////////////////////////////////////////////////////////////////
	//관리자 페이지 list.html 전달
	@Override
	public String list(Model model) {
		VisualRole vRole= VisualRole.VLIST;
		model.addAttribute("vlist", 
				repository.findAllByVisualRole(vRole)
				.stream().map(VisualListDTO::new).collect(Collectors.toList()));
		return "admin/visual/list";
	}
	@Override
	public boolean updateIsShow(long vno, boolean isShow) {
		Optional<VisualFile> result=repository.findById(vno);
		if(result.isEmpty()) return false;
		repository.save(result.get().updateIsShow(isShow));
		return true;
	}

	@Override
	public boolean updateData(long vno, VisualUpdateDTO dto) {
		Optional<VisualFile> result= repository.findById(vno);
		if(result.isEmpty())return false;
		
		repository.save(result.get().updateData(dto));
		return true;
	}
	//메인홈 index main상단출력이미지를 받는 list 
	@Override
	public String indexList(Model model) {
		// "/visual/list.html"위치에 보내지는 list, 하단 쿼리메서드 생성가능
		model.addAttribute("vlist", repository.findAllByIsShowAndVisualRole(true,VisualRole.VLIST)
				.stream().map(VisualListDTO::new).collect(Collectors.toList()));
		model.addAttribute("elist", repository.findAllByIsShowAndVisualRole(true,VisualRole.ELIST)
				.stream().map(VisualListDTO::new).collect(Collectors.toList()));
		return "/visual/list";
	}
	
	/////elist////////////
	
	@Override
	public String saveElist(MultipartFile file, VisualInsertDTO dto) {
		String url="/images/visual/elist/";//업로드할 파일 위치
		FileData fileData= MyFileUtils.upLoad(file, url);
		dto.addFileData(fileData);
		repository.save(dto.toVisuslFile().addRole(VisualRole.ELIST));
		return "redirect:/admin/elist";
	}
	@Override
	public String elist(Model model) {
		VisualRole eRole = VisualRole.ELIST;
		model.addAttribute("elist", repository.findAllByVisualRole(eRole)
				.stream().map(VisualListDTO::new).collect(Collectors.toList()));
		return "/admin/visual/elist";
	}
		
	//////////////////////
	@Override
	public void delete(long vno) {
		repository.deleteById(vno);
		
	}
	@Override
	public void elistpage(Model model) {
		model.addAttribute("elist", repository.findAllByIsShowAndVisualRole(true,VisualRole.ELIST)
				.stream().map(VisualListDTO::new).collect(Collectors.toList()));
		
		
	}
	

}
