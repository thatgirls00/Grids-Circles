package org.example.cafe.domain.product.service;


import lombok.RequiredArgsConstructor;
import org.example.cafe.domain.product.dto.admin.AdminProductRequestDto;
import org.example.cafe.domain.product.dto.admin.AdminProductResponseDto;
import org.example.cafe.domain.product.entity.Product;
import org.example.cafe.domain.product.repository.ProductRepository;
import org.example.cafe.global.error.CustomException;
import org.example.cafe.global.error.ErrorCode;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final ProductRepository productRepository;
    private final ProductService productService;

    // 상품 등록
    public AdminProductResponseDto createProduct(AdminProductRequestDto requestDto) {
        // 중복 체크
        productRepository.findByName(requestDto.getName()).ifPresent(p -> {
            throw new CustomException(ErrorCode.PRODUCT_ALREADY_EXISTS);
        });
        
        Product saved = productRepository.save(requestDto.toEntity());
        System.out.println("product saved: " + saved.getName()+" "+saved.getPrice()+"원");
        return AdminProductResponseDto.fromEntity(saved);
    }

    // 관리자가 상품 수정
    public AdminProductResponseDto updateProduct(Long productId, AdminProductRequestDto requestDto) {
        Product product = getProductOrThrow(productId);

        product.update(requestDto.getName(), requestDto.getPrice(), requestDto.getQuantity());
        product.setDescription(requestDto.getDescription());

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
}