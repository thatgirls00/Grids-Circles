package org.example.cafe.frontTestcontroller;

import lombok.RequiredArgsConstructor;
import org.example.cafe.domain.product.dto.ProductDto;
import org.example.cafe.domain.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class testViewController {

    private final ProductService productService;

    @GetMapping("/index")
    public String showIndexPage(Model model) {
        List<ProductDto> products = productService.findAll()
                .stream()
                .map(ProductDto::new)
                .toList();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/user")
    public String showUserPage() {
        return "user";
    }

    @GetMapping("/admin_login")
    public String showAdminLogin() {
        return "admin_login";
    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }

    @GetMapping("/order")
    public String redirectToIndex() {
        return "index"; // templates/index.html 로 연결
    }

    // 관리자 로그인 처리
    @PostMapping("/login")
    public String handleLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        if ("admin@admin.com".equals(username) && "admin".equals(password)) {
            return "redirect:/admin";
        } else {
            return "redirect:/admin_login?error=true";
        }
    }
}