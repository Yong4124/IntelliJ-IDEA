package com.du.myproject1.controller;

import com.du.myproject1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor   // ✅ 이게 꼭 있어야 final 필드가 주입됩니다!
public class AuthController {

    private final UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new com.du.myproject1.domain.User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute com.du.myproject1.domain.User user) {
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
}