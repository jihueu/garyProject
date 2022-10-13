package com.nowon.garyProject.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.garyProject.domain.dto.FileData;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MyFileUtils {
	
	public static FileData upLoad(MultipartFile multipartFile, String url) {
		
		String orgName = multipartFile.getOriginalFilename();
		String newName = null;
		long size=multipartFile.getSize();
		
		ClassPathResource cpr= new ClassPathResource("static"+url);
		
		try {
			File location = cpr.getFile();
			multipartFile.transferTo(new File(location, orgName));
			log.info(">>>>>>>>파일업로드 완료!!");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return FileData.builder()
				.url(url).orgName(orgName).size(size).newName(newName)
				.build();
	}

}
