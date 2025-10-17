package com.du.newsec.controller;

import com.du.newsec.entity.User;
import com.du.newsec.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerForm(){ return "register"; }

    @PostMapping("/register")
    @Transactional
    public String register(@RequestParam String username, @RequestParam String password){
        User user = new  User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() { return "login"; }

    @GetMapping("/home")
    public String home() { return "home"; }
}
