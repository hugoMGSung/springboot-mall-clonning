package com.hugo83.webmall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// 스프링시큐리티 핵심파일!!
@Configuration      // 스프링 환경설정 파일 
@EnableWebSecurity  // 스프링시큐리티를 제어 활성화
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 인증되지 않은 모든 페이지 요청을 허락(로그인창 안뜸)
            .authorizeHttpRequests(auth -> auth.requestMatchers("/**").permitAll()
                                               .anyRequest().authenticated())
            .csrf(csrf -> csrf.disable())  
            // 로그인URL 접근 지정. 로그인페이지 URL과 로그인성공후 페이지 URL 지정
            .formLogin(fmli -> fmli.loginPage("/members/login")
                                 .defaultSuccessUrl("/"))
            // 로그아웃URL 지정.
            .logout(lgot -> lgot.logoutUrl("/members/logout")
                              .logoutSuccessUrl("/")
                              .invalidateHttpSession(true))
        ;  // ;을 분리해놓으면 chain method 추가시 간편함
            
        return http.build();
    }

    // 회원가입, 로그인시 동일하게 사용(로그인시 스프링 시큐리티가 자동으로 사용)
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // 회원가입 패스워드 암호화시 사용한 엔코더와 동일
    }
}