package org.example.cafe.domain.product.dto;

import lombok.*;
import org.example.cafe.domain.product.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    Long id;
    String name;
    int price;
    int quantity;
    List<String> imageUrls;
    String description;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.imageUrls = product.getImages()
                .stream()
                .map(img -> img.getUrl())
                .collect(Collectors.toList());
    }
}