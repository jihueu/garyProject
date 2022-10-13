package com.nowon.garyProject.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.garyProject.domain.dto.board.JpaBoardInserDTO;
import com.nowon.garyProject.domain.dto.board.JpaBoardUpdateDTO;
import com.nowon.garyProject.domain.dto.board.ReplyInsertDTO;

public interface JpaBoardService {

	void save(MultipartFile[] file, JpaBoardInserDTO dto);
	
	void list(int pageNo, Model model);

	void detail(long no, Model model);

	void update(JpaBoardUpdateDTO dto, long no);

	void delete(long no);

	boolean reply(ReplyInsertDTO dto);

	String replies(long bno, Model model);

	String fileUpload(MultipartFile file, String prevImgName);



	


	

}
