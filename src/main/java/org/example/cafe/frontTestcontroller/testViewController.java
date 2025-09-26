package org.example.cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class testViewController {

    @GetMapping("/index")
    public String showIndexPage() {
        return "index"; // templates/index.html
    }

    @GetMapping("/user")
    public String showUserPage() {
        return "user"; // templates/user.html
    }

    @GetMapping("/admin_login")
    public String showAdminLogin() {
        return "admin_login"; // templates/admin_login.html
    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin"; // templates/admin.html
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