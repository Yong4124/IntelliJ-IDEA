package com.du.quiz1.controller;

import com.du.quiz1.domain.Menu;
import com.du.quiz1.domain.Review;
import com.du.quiz1.service.MenuService;
import com.du.quiz1.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class MenuController {

    private final MenuService menuService;
    private final ReviewService reviewService;

    /** 목록 */
    @GetMapping({"/", "/menus"})
    public String list(Model model) {
        model.addAttribute("menus", menuService.findAll());
        return "list"; // templates/list.html
    }

    /** 작성 폼 */
    @GetMapping("/menus/new")
    public String createForm(Model model) {
        model.addAttribute("menu", new Menu());
        return "form"; // templates/form.html
    }

    /** 작성 처리 (id 없으면 insert) */
    @PostMapping("/menus")
    public String create(@ModelAttribute Menu menu) {
        menuService.save(menu);               // useGeneratedKeys로 menu.id 세팅됨
        return "redirect:/menus/" + menu.getId();
    }

    /** 수정 폼 */
    @GetMapping("/menus/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("menu", menuService.findById(id));
        return "form"; // templates/form.html
    }

    /** 수정 처리 (id 있으면 update) */
    @PostMapping("/menus/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Menu form) {
        form.setId(id);
        menuService.save(form);
        return "redirect:/menus/" + id;
    }

    /** 삭제 */
    @PostMapping("/menus/{id}/delete")
    public String delete(@PathVariable Long id) {
        menuService.delete(id);
        return "redirect:/menus";
    }

    /** 상세 */
    @GetMapping("/menus/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Menu menu = menuService.findById(id);
        model.addAttribute("menu", menu);
        model.addAttribute("reviews", reviewService.listByMenu(id));
        model.addAttribute("avgRating", reviewService.avgRating(id));
        model.addAttribute("review", new Review()); // 리뷰 작성 폼 바인딩용
        return "detail"; // templates/detail.html
    }

    /** 리뷰 등록 */
    @PostMapping("/menus/{id}/reviews")
    public String addReview(@PathVariable Long id, @ModelAttribute("review") Review review) {
        review.setMenuId(id);
        reviewService.create(review);
        return "redirect:/menus/" + id;
    }

    /** 리뷰 삭제 */
    @PostMapping("/menus/{menuId}/reviews/{reviewId}/delete")
    public String deleteReview(@PathVariable Long menuId, @PathVariable Long reviewId) {
        reviewService.delete(reviewId);
        return "redirect:/menus/" + menuId;
    }
}
