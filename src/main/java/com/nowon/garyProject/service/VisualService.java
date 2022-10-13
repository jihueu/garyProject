package com.nowon.garyProject.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.garyProject.domain.dto.visual.VisualInsertDTO;
import com.nowon.garyProject.domain.dto.visual.VisualUpdateDTO;

public interface VisualService {

	String save(MultipartFile vimg, VisualInsertDTO dto);

	String list(Model model);

	String indexList(Model model);

	boolean updateIsShow(long vno, boolean isShow);

	boolean updateData(long vno, VisualUpdateDTO dto);

	String saveElist(MultipartFile vimg, VisualInsertDTO dto);

	String elist(Model model);

	void delete(long no);

	void elistpage(Model model);

}
