package org.example.cafe.domain.product.controller;

import lombok.RequiredArgsConstructor;
import org.example.cafe.domain.product.dto.ProductDeleteDto;
import org.example.cafe.domain.product.dto.ProductDto;
import org.example.cafe.domain.product.entity.Product;
import org.example.cafe.domain.product.service.ProductService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    //상품 목록 조회 -> DB확인까지완료
    @GetMapping("/api/products")
    @Transactional(readOnly = true)
    public Map<String, Object> getProducts() {
        List<ProductDto> productList = productService.findAll()
                .stream()
                .map(ProductDto::new)
                .toList();

        return Map.of("data", productList);
    }

    //상품 등록 -> DB확인까지완료
    @PostMapping("/api/products")
    @Transactional
    public ProductDto createProduct(
            @RequestBody ProductDto productDto
    ){
        Product product = productService.create(productDto.getName(),productDto.getPrice(),productDto.getQuantity());

        return new ProductDto(product);
    }

    //상품 수정 -> DB확인까지완료
    @PutMapping("/api/products")
    @Transactional
    public ProductDto updateProduct(
            @RequestBody ProductDto productDto
    ){
        Product product = productService.findById(productDto.getId()).get();
        productService.modify(product, productDto.getName(), productDto.getPrice(), productDto.getQuantity());

        return new ProductDto(product);
    }


    //상품 삭제 -> DB확인까지완료
    @DeleteMapping("/api/products")
    @Transactional
    public void deleteProduct(
            @RequestBody ProductDeleteDto productDeleteDto
    ){
        Product product = productService.findById(productDeleteDto.getId()).get();
        productService.delete(product);
    }

}
