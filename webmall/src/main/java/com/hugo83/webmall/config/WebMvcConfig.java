package com.hugo83.webmall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${uploadPath}")
    String uploadPath;

    @Override
    public void addResourceHandlers(@SuppressWarnings("null") ResourceHandlerRegistry registry) {
        /// url에 /images로 시작하는 경위 uploadPath에 설정한 폴더를 기준으로 파일로드
        registry.addResourceHandler("/images/**")  
                .addResourceLocations(uploadPath);
    }
}
