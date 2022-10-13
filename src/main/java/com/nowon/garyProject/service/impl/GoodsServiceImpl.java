package com.nowon.garyProject.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.garyProject.domain.dto.goods.CategoryDto;
import com.nowon.garyProject.domain.dto.goods.GoodsDetailDTO;
import com.nowon.garyProject.domain.dto.goods.GoodsInsertDTO;
import com.nowon.garyProject.domain.dto.goods.GoodsListDTO;
import com.nowon.garyProject.domain.entity.CategoryRepository;
import com.nowon.garyProject.domain.entity.item.Goods;
import com.nowon.garyProject.domain.entity.item.GoodsFile;
import com.nowon.garyProject.domain.entity.item.GoodsRepository;
import com.nowon.garyProject.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	GoodsRepository repository;
	
	
	@Autowired
	CategoryRepository categoryRepository;
	
	
	@Override
	public String tempFileUpload(MultipartFile file) {
		String path="/images/goods/temp/";
		ClassPathResource cpr=new ClassPathResource("Static" + path);
		
		try {
			File folder=cpr.getFile();
			File targetFile=new File(folder, file.getOriginalFilename());
			file.transferTo(targetFile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path+file.getOriginalFilename() ;
	}

	@Override
	public String save(GoodsInsertDTO dto) {
		//서버에 파일이름
		String def=dto.getDefImg();
		String gfile=dto.getGImg();
		
		String path="/images/goods/temp/";
		ClassPathResource cpr=new ClassPathResource("static"+path);
		
		Goods entity=dto.toEntity();
		
		try {
			//ClassPathResource target=new ClassPathResource("Static"+"/images/goods");
			
			File root = cpr.getFile();
			////상품이미지///////////////////////////
			File defFile=new File(root, def);
			defFile.renameTo(new File(root.getParent(), def));
			String name=defFile.getName();
			System.out.println(name);
			long size=defFile.length();
			
			GoodsFile defgoods= GoodsFile.builder()
			.newName(name).orgName(name).size(size).url("/images/goods/")
			.isDefImg(true)
			.build();
			//////////////////////////////////////////
			////추가이미지//////////////////////////////
			File gFile=new File(root, gfile);
			gFile.renameTo(new File(root.getParent(), gfile));//파일이름
			name=gFile.getName();
			System.out.println(name);
			size=gFile.length();
			
			GoodsFile gFilegoods= GoodsFile.builder()
			.newName(name).orgName(name).size(size).url("/images/goods/")
			.build();
		
			repository.save(entity
					.addFile(defgoods)
					.addFile(gFilegoods)
					.addCategory(categoryRepository.findById(dto.getCaNo()).get()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:glist";//넘어가는 페이지 glist
	}

	@Override
	public String list(Model model) {
		
		List<GoodsListDTO> result= repository.findAll()
				.stream()
				.map(GoodsListDTO::new)
				.collect(Collectors.toList());//엔터티로 들어오니까 디티오로 변환해주자
		model.addAttribute("glist",result);
		return "admin/goods/glist";
	}

	@Override
	public String indexGlist(Model model) {
		//List<GoodsEntity> -> List<GoodsListDTO>
		List<GoodsListDTO> result= repository.findAll()
				.stream()
				.map(GoodsListDTO::new)
				.collect(Collectors.toList());//엔터티로 들어오니까 디티오로 변환해주자
		
		model.addAttribute("glist",result);
		return "goodspage/glist-data";
	}

	@Override
	public String detail(long gno, Model model) {
		
		model.addAttribute("detail", repository.findById(gno).map(GoodsDetailDTO::new).get());
		
		return "goodspage/goods-detail";
	}
	
	
	@Override
	public List<CategoryDto> categoryList(long caNo) {
		// 1100 ~~ 1101~1199 Between을 활용하여
		return categoryRepository.findByCaNoBetween(caNo, caNo+99).stream()
				.map(CategoryDto::new)
				.toList();
	}

	
	
	@Override
	public void goodsListByCategory(long caNo, Model model) {
		model.addAttribute("list", repository.findAllByCategorysCaNoBetween(caNo, caNo+99)
											.stream()
											.map(GoodsListDTO::new)
											.toList());
	}

	@Override
	public void delete(long gno) {
		
//		repository.findById(gno).map(g->g.removeCategoryAll());
		//매니투매니 여서 FK를 먼저삭제해주고 굿즈테이블내용삭제
		repository.delete(repository.findById(gno).map(g->g.removeCategoryAll()).get());
	}

}
