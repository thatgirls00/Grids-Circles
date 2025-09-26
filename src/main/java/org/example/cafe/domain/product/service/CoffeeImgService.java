package org.example.cafe.domain.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cafe.domain.product.entity.CoffeeImg;
import org.example.cafe.domain.product.entity.Product;
import org.example.cafe.domain.product.repository.CoffeeImgRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CoffeeImgService {
    private final CoffeeImgRepository coffeeImgRepository;

    @Value("${file.path}")
    private String uploadPath;

    public List<CoffeeImg> saveImg(List<MultipartFile> imgs, Product coffee) {

        List<CoffeeImg> coffeeImgs = imgs.stream()
                .map(file -> {
                    try {

                        String originalFilename  = file.getOriginalFilename();
                        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                        String newFilename = UUID.randomUUID() + extension;

                        Path savePath = Paths.get(uploadPath, newFilename);

                        file.transferTo(savePath);

                        return CoffeeImg.builder()
                                .title(file.getOriginalFilename())
                                .url("/img/" + newFilename)
                                .coffee(coffee)
                                .build();
                    } catch (IOException e) {
                        throw new RuntimeException("이미지 파일 업로드 실패" + e.getMessage());
                    }
                })
                .toList();

        return coffeeImgs;

    }

}

