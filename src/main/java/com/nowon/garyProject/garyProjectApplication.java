package com.nowon.garyProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class garyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(garyProjectApplication.class, args);
	}
	 @Bean
	    PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
	/*
	  @Bean //hidden 파라미터적용하기위해서 properties 또는 이렇게 빈설정 HiddenHttpMethodFilter
	  hiddenHttpMethodFilter() { return new HiddenHttpMethodFilter(); }
	
		//applocation.properties 에 설정가능
		//spring.mvc.hiddenmethod.filter.enabled=true
		*/


}
