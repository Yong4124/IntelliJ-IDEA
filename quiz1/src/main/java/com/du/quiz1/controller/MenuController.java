package com.du.quiz1.controller;

import com.du.quiz1.domain.Menu;
import com.du.quiz1.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    // 목록
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("menus", menuService.findAll());
        return "list";
    }

    // 등록 폼
    @GetMapping("/menus/new")
    public String createForm(Model model) {
        Menu menu = new Menu();
        menu.setAvailable(true);
        menu.setSpicyLevel(0);
        model.addAttribute("menu", menu);
        return "form";
    }

    // 저장 (등록/수정 공용)
    @PostMapping("/menus")
    public String save(Menu menu) {
        menuService.save(menu);
        return "redirect:/";
    }

    // 수정 폼
    @GetMapping("/menus/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("menu", menuService.findById(id));
        return "form";
    }

    // 삭제
    @PostMapping("/menus/{id}/delete")
    public String delete(@PathVariable Long id) {
        menuService.delete(id);
        return "redirect:/";
    }
}