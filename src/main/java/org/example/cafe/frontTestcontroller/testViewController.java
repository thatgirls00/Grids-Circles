package org.example.cafe.frontTestcontroller;

import lombok.RequiredArgsConstructor;
import org.example.cafe.domain.member.service.MemberService;
import org.example.cafe.domain.product.dto.ProductDto;
import org.example.cafe.domain.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String showUserLogin() {
        return "user_login";
    }

    @PostMapping("/user_login")
    public String handleUserLogin(@RequestParam String email,
                                  @RequestParam(required = false) String password) {
        if (memberService.existsByEmail(email)) {
            return "redirect:/user";
        }
        return "redirect:/user_login?error";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/admin_login")
    public String showAdminLogin() {
        return "admin_login";
    }

    @GetMapping("/order")
    public String redirectToIndex() {
        return "index"; // templates/index.html
    }
}