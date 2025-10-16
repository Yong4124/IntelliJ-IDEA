package com.du.myproject1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectToPosts() {
        // 기본 루트(/) 접근 시 게시글 목록으로 이동
        return "redirect:/posts";
    }
}
