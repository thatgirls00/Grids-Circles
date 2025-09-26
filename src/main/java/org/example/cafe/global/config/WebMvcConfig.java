package org.example.cafe.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 정적 리소스 (resources/static/img/) 매핑 추가
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/");

        // 업로드 파일 매핑
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:/Users/jeon/supernova-upload/img/");
    }
}