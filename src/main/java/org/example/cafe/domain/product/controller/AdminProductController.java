package org.example.cafe.domain.product.controller;

import lombok.RequiredArgsConstructor;
import org.example.cafe.domain.product.dto.admin.AdminProductRequestDto;
import org.example.cafe.domain.product.dto.admin.AdminProductResponseDto;
import org.example.cafe.domain.product.service.AdminProductService;
import org.example.cafe.global.rsData.RsData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService adminProductService;

    // 상품 등록
    @PostMapping
    public ResponseEntity<RsData<AdminProductResponseDto>> createProduct(
            @RequestBody AdminProductRequestDto requestDto
    ) {
        AdminProductResponseDto response = adminProductService.createProduct(requestDto);
        return ResponseEntity.ok(new RsData<>("200", "상품 등록 완료", response));
    }

    // 상품 수정
    @PutMapping("/{id}")
    public ResponseEntity<RsData<AdminProductResponseDto>> updateProduct(
            @PathVariable Long id,
            @RequestBody AdminProductRequestDto requestDto
    ) {
        AdminProductResponseDto response = adminProductService.updateProduct(id, requestDto);
        return ResponseEntity.ok(new RsData<>("200", "상품 수정 완료", response));
    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<RsData<Void>> deleteProduct(@PathVariable Long id) {
        adminProductService.deleteProduct(id);
        return ResponseEntity.ok(new RsData<>("200", "상품 삭제 완료", null));
    }
}
