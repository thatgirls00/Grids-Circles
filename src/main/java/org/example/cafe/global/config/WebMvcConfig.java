package org.example.cafe.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${app.file.upload-dir}")
    private String uploadDir; // C:/Users/admin/upload-data/cafe_images

    @Value("${app.file.base-url}")
    private String baseUrl;   // /images/
/*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        *//*
        // 정적 리소스 (resources/static/img/) 매핑 추가
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/");

        // 업로드 파일 매핑
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:/Users/jeon/supernova-upload/img/");

*//*
        // 1. 기본 정적 리소스 매핑 (유지)
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/");

        // 2. 업로드 파일 매핑 (설정 기반으로 변경)
        // Spring이 외부 디렉토리를 인식하도록 file:/// URI 형식으로 변환
        String resourceLocation = Paths.get(uploadDir).toUri().toString();

        if (!resourceLocation.endsWith("/")) {
            resourceLocation += "/";
        }

        // 웹 요청: /images/** ->  물리 경로: file:///C:/Users/admin/upload-data/cafe_images/
        registry.addResourceHandler(baseUrl + "**")
                .addResourceLocations(resourceLocation);
    }*/

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 1. 기본 정적 리소스 매핑 (유지)
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/");

        // 2. 업로드 파일 매핑 (Windows 호환성 강화를 위해 수정)

        // a. Windows 경로 구분자(\)를 웹 경로 구분자(/)로 통일
        String normalizedPath = uploadDir.replace("\\", "/");

        // b. 'file:' 접두사를 붙여 URI 형태로 만듭니다. (Spring이 권장하는 방식)
        String locationUri = "file:" + normalizedPath;

        // c. 경로 끝에 /가 없으면 추가
        if (!locationUri.endsWith("/")) {
            locationUri += "/";
        }

        // 🎯 웹 요청: /images/** -> 물리적 경로: file:C:/Users/admin/upload-data/cafe_images/
        registry.addResourceHandler(baseUrl + "**")
                .addResourceLocations(locationUri);

        // ✅ 최종 매핑 경로 확인용 로그 (문제 해결 시 삭제)
        System.out.println("=========================================================");
        System.out.println("✅ Resource Handler Mapping: " + baseUrl + "** -> " + locationUri);
        System.out.println("=========================================================");
    }
}