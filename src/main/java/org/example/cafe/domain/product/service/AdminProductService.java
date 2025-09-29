package org.example.cafe.domain.product.service;


import lombok.RequiredArgsConstructor;
import org.example.cafe.domain.product.dto.admin.AdminProductRequestDto;
import org.example.cafe.domain.product.dto.admin.AdminProductResponseDto;
import org.example.cafe.domain.product.entity.CoffeeImg;
import org.example.cafe.domain.product.entity.Product;
import org.example.cafe.domain.product.repository.ProductRepository;
import org.example.cafe.global.error.CustomException;
import org.example.cafe.global.error.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminProductService {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @Value("${app.file.upload-dir}")
    private String uploadDir;

    // application.yml에서 주입: /images/
    @Value("${app.file.base-url}")
    private String baseUrl;

    // 상품 등록
    public AdminProductResponseDto createProduct(AdminProductRequestDto requestDto) {
        System.out.println("=========================================================");
        System.out.println("✅ WebMvcConfig: 주입된 uploadDir 경로 확인: " + this.uploadDir);
        System.out.println("✅ WebMvcConfig: 주입된 baseUrl 경로 확인: " + this.baseUrl);
        System.out.println("=========================================================");
        // 중복 체크
        productRepository.findByName(requestDto.getName()).ifPresent(p -> {
            throw new CustomException(ErrorCode.PRODUCT_ALREADY_EXISTS);
        });
        
        Product saved = productRepository.save(requestDto.toEntity());
        System.out.println("product saved: " + saved.getName()+" "+saved.getPrice()+"원");

// 3. 파일 처리 및 CoffeeImg 엔티티 생성/연결
        if (requestDto.getImages() != null) {
            for (MultipartFile file : requestDto.getImages()) {
                if (!file.isEmpty()) {
                    try {
                        // 파일 저장 후 DB에 저장할 웹 접근 URL 획득
                        String dbUrl = saveFile(file, saved.getId());

                        // CoffeeImg 엔티티 생성 및 Product에 연결
                        CoffeeImg img = CoffeeImg.createImageEntity(
                                saved,
                                file.getOriginalFilename(), // 원본 파일명을 제목으로 사용
                                dbUrl
                        );
                        saved.addImage(img); // Product 엔티티의 연관 관계 편의 메서드 사용

                    } catch (IOException e) {
                        // 파일 저장 실패 시 트랜잭션 롤백 유도 (체크 예외를 런타임 예외로 변환)
                        throw new CustomException(ErrorCode.EXAMPLE_ERROR_CODE);
                    }
                }
            }
        }

        return AdminProductResponseDto.fromEntity(saved);
    }

    // 관리자가 상품 수정
    public AdminProductResponseDto updateProduct(Long productId, AdminProductRequestDto requestDto) {
        Product product = getProductOrThrow(productId);

        product.update(requestDto.getName(), requestDto.getPrice(), requestDto.getQuantity());
        product.setDescription(requestDto.getDescription());

        // 2. 파일 처리 로직
// images 배열이 null이 아니고, 길이가 0보다 크며, 첫 번째 파일이 비어있지 않은지 확인
        MultipartFile[] files = requestDto.getImages();

        if (files != null && files.length > 0 && !files[0].isEmpty()) {

            // 2-1. 기존 이미지 삭제 (DB 엔티티와 물리적 파일 모두)
            deleteAllImages(product);

            // 2-2. 새로운 이미지 저장 및 DB 엔티티 연결
            // 배열을 순회합니다. (files[0].isEmpty()는 이미 확인했으므로, 나머지만 확인)
            for (MultipartFile file : files) {
                if (!file.isEmpty()) { // 배열의 다른 요소가 null일 수 있으므로 안전하게 다시 확인
                    try {
                        // 파일 저장 로직은 기존대로 유지
                        String dbUrl = saveFile(file, product.getId());

                        CoffeeImg img = CoffeeImg.createImageEntity(
                                product,
                                file.getOriginalFilename(),
                                dbUrl
                        );
                        product.addImage(img);

                    } catch (IOException e) {
                        throw new CustomException(ErrorCode.EXAMPLE_ERROR_CODE);
                    }
                }
            }
        }

        Product updated = productRepository.save(product);
        return AdminProductResponseDto.fromEntity(updated);
    }

    // 관리자가 상품 삭제
    public void deleteProduct(Long productId) {
        Product product = getProductOrThrow(productId);
        productRepository.delete(product);
    }

    private Product getProductOrThrow(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    /**
     * 상품에 연결된 모든 CoffeeImg 엔티티를 삭제하고, 연결된 물리적 파일을 디스크에서 삭제합니다.
     * @param product 이미지가 연결된 상품 엔티티
     */
    private void deleteAllImages(Product product) {
        // 엔티티를 복사하여 ConcurrentModificationException 방지
        for (CoffeeImg img : new java.util.ArrayList<>(product.getImages())) {

            // 1. 물리적 파일 삭제 시도
            // URL에서 파일 이름만 추출: /images/1_uuid.png -> 1_uuid.png
            String fileName = img.getUrl().substring(baseUrl.length());
            Path filePath = Paths.get(uploadDir, fileName);

            try {
                if (Files.exists(filePath)) {
                    Files.delete(filePath);
                    System.out.println("✅ 물리적 파일 삭제 성공: " + fileName);
                }
            } catch (IOException e) {
                // 파일이 없거나 삭제 권한이 없는 경우, 로그만 남기고 DB 삭제는 진행
                System.err.println("⚠️ 물리적 파일 삭제 실패: " + fileName + ". DB 엔티티 삭제는 계속 진행됩니다.");
                e.printStackTrace();
            }

            // 2. Product 엔티티에서 CoffeeImg 연결 해제 및 DB에서 삭제
            // (CoffeeImg 엔티티에 removeImageFromProduct 또는 cascading 설정이 되어 있어야 합니다.)
            // Product의 편의 메서드를 통해 양방향 관계 해제 및 CascadeType.ALL 설정을 통해 삭제
            product.removeImage(img);
        }
    }

    private String saveFile(MultipartFile file, Long productId) throws IOException {
        // 1. 저장 디렉토리 준비
        // uploadDir = "C:/Users/admin/upload-data/cafe_images"
        Path uploadPath = Paths.get(uploadDir);

        // 디렉토리가 없으면 생성 (재귀적으로 상위 폴더까지)
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 2. 파일명 생성 (ID_UUID.확장자 형태로 중복 방지)
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // Product ID와 UUID를 결합하여 고유한 파일명 생성
        String uniqueFilename = productId + "_" + UUID.randomUUID().toString() + extension;
        Path targetPath = uploadPath.resolve(uniqueFilename);

        // 3. 파일을 지정된 경로에 저장
        file.transferTo(targetPath.toFile());

        // 4. DB에 저장할 URL 반환 (웹에서 접근 가능한 경로)
        // 예: /images/123_uuid.jpg

        return baseUrl + uniqueFilename;
    }
}