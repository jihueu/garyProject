package com.nowon.garyProject.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.garyProject.service.GoodsService;

@Controller
public class SummernoteController {
	
	@Autowired
	GoodsService service;
		
	//ajax
	@ResponseBody
	@PostMapping("/admin/uploadSImg")
	public String uploadSimg(MultipartFile file) {
		
		if(!file.getContentType().contains("image"))return null;
		
		String url="/images/summernote/";
		ClassPathResource cpr=new ClassPathResource("static"+url);
		String orgName = file.getOriginalFilename();
		String saveName = UUID.randomUUID()+"_"+orgName;
		
		try {
			File location=cpr.getFile();
			file.transferTo(new File(location, saveName));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url+saveName;
	}
	
	
}
