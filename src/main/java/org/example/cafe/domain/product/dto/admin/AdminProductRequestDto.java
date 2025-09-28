package org.example.cafe.domain.product.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.cafe.domain.product.entity.Product;

@Getter
@Setter
@NoArgsConstructor
public class AdminProductRequestDto {
    private String name;
    private int price;
    private int quantity;
    private String description;
    // TODO: 이미지 업로드가 필요하다면 여기서 imageUrls 대신 MultipartFile[] 같은 걸로 처리

    public Product toEntity() {
        Product product = new Product(this.name, this.price, this.quantity);
        product.setDescription(this.description);
        return product;
    }
}