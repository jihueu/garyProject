package com.nowon.garyProject.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
	
	private final CustomOAuth2UserService customOAuth2UserService;
	private final CustomUserDetailsService customUserDetailsService;
	private final DataSource dataSource;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorize 
									-> authorize
												.antMatchers("/", "/common/**","/error","/request-key/*").permitAll()
												.antMatchers("/admin/**").hasRole("ADMIN")
												.antMatchers("/board/**").hasRole("USER")
												.anyRequest().authenticated());

		http.oauth2Login(oauth2Login 
								-> oauth2Login.loginPage("/signin")
												//.defaultSuccessUrl("/")
												.userInfoEndpoint()
												.userService(customOAuth2UserService));
		
		http.formLogin(formLogin 
								-> formLogin
											.loginPage("/signin")//로그인페이지 이동 url
											.usernameParameter("email")
											.passwordParameter("pass")
											.failureUrl("/signin?error")//로그인 실패시 url
											.loginProcessingUrl("/signin")//form태그에서 요청되는 주소:action값
											.defaultSuccessUrl("/")//로그인성공하고 메인페이지로 넘어가는 설정, true설정이 중요
											//.successHandler(successhandler())//성공시 처리 그러나 다른방법 사용
											//.failureHandler(failureHandler())//로그인실패시 처리
											.permitAll());
		http.logout(logout
							->logout
								.logoutSuccessUrl("/")
								.invalidateHttpSession(true));//default is "/login?logout"
		http.rememberMe()
			.rememberMeParameter("remember-me")
			.tokenValiditySeconds(86400*30)
			.userDetailsService(customUserDetailsService);
		
		http.csrf();
		return http.build();
	}
	@Bean
    PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
	@Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**/*", "/js/**","/images/**/*");
    } 
	
	/*이렇게 추가해서 만들 수도 있음
	 * @Bean 
	 * public SecurityFilterChain filterChain2(HttpSecurity http) throws
	 * Exception { http .authorizeRequests(authorize -> authorize
	 * 
	 * .antMatchers("/", "/board").permitAll() .anyRequest() .authenticated() );
	 * 
	 * return http.build(); }
	 */

}
