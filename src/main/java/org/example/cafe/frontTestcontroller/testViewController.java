package org.example.cafe.frontTestcontroller;

import lombok.RequiredArgsConstructor;
import org.example.cafe.domain.member.entity.Member;
import org.example.cafe.domain.member.service.MemberService;
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
    private final MemberService memberService;

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

    @GetMapping("/user_login")
    public String showUserLogin() {return "user_login";}

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

    @PostMapping("/login")
    public String handleLogin(@RequestParam("email") String email,
                              @RequestParam(value = "password", required = false) String password) {

        // 관리자 로그인
        if ("admin@admin.com".equals(email) && "admin".equals(password)) {
            return "redirect:/admin";
        }

        // 사용자 로그인 - DB 이메일 확인
        if (memberService.existsByEmail(email)) {
            return "redirect:/user";
        }

        // 실패
        return "redirect:/user_login?error=true";
    }
}