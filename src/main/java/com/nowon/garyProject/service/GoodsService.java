package com.nowon.garyProject.service;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.garyProject.domain.dto.goods.CategoryDto;
import com.nowon.garyProject.domain.dto.goods.GoodsInsertDTO;

public interface GoodsService {


	String tempFileUpload(MultipartFile file);

	String save(GoodsInsertDTO dto);

	String list(Model model);

	String indexGlist(Model model);

	String detail(long gno, Model model);

	List<CategoryDto> categoryList(long caNo);

	void goodsListByCategory(long caNo, Model model);

	void delete(long gno);

}
