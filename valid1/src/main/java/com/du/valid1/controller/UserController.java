package com.du.valid1.controller;

import com.du.valid1.MyUserRepository;
import com.du.valid1.dto.UserRequest;
import com.du.valid1.entity.MyUser;
import com.du.valid1.util.PasswordUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final MyUserRepository myUserRepository;

    @GetMapping("/signup")
    public String showSignup(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@Valid @ModelAttribute("userRequest") UserRequest userRequest,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }


//        MyUser myUser = userRequest.toEntity();
        String hashedPassword = PasswordUtil.hashPassword(userRequest.getPassword());
        MyUser user = userRequest.toEntity(hashedPassword);
        myUserRepository.save(user);

        model.addAttribute("message", "회원가입이 성공적으로 완료되었습니다.");
        return "signup"; // (PRG 원하면 redirect:/signup 으로 변경)
    }
}
