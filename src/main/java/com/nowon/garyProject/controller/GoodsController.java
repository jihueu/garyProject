package com.nowon.garyProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.garyProject.domain.dto.goods.GoodsInsertDTO;
import com.nowon.garyProject.domain.entity.CategoryA;
import com.nowon.garyProject.service.GoodsService;

@Controller
public class GoodsController {

	@Autowired
	GoodsService service;
	
	@GetMapping("/common/category/{caNo}/goods")
	public String goodsByCategory(@PathVariable long caNo,Model model ) {
		service.goodsListByCategory(caNo,model);
		return "/goodspage/list";
	}
	
	
	
	@GetMapping("/common/goods")
	public String indexGlist(Model model) {
		
		return service.indexGlist(model);
	}
	
	@GetMapping("/common/goods/{gno}")
	public String detail(@PathVariable long gno ,Model model) {
		
		return service.detail(gno, model);
	}

	@GetMapping("/admin/category/{caNo}")
	public String category(@PathVariable long caNo, Model model ) {
		model.addAttribute("option", service.categoryList(caNo));
		return "admin/goods/category-data";
	}
	
	@ResponseBody
	@PostMapping("/admin/goods/fileupload")
	public String tempFileupload(MultipartFile file) {
		
		return service.tempFileUpload(file);
		
	}
	
	@ResponseBody
	@DeleteMapping("/goods/glist/{gno}")
	public void delete(@PathVariable long gno) {
		service.delete(gno);
	}
	
	//관리자홈-굿즈상품리스트페이지이동컨트롤러
	@GetMapping("/admin/glist")
	public String goodslist(Model model) {
		
		return service.list(model);
	}
	
	@PostMapping("/admin/goods")
	public String goodsInsert(GoodsInsertDTO dto) {
		System.out.println("마 나와따" + dto.getCaNo());
		return service.save(dto);
	}
	
	@GetMapping("/admin/gwrite")
	public String goodswrite(Model model) {
		model.addAttribute("cateA", CategoryA.values());
		return "admin/goods/gwrite";
	}
	
	
}
