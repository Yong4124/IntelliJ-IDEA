package com.du.myproject1.controller;

import com.du.myproject1.domain.Post;
import com.du.myproject1.service.CommentService;
import com.du.myproject1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    // 목록 (페이징 추가)
    @GetMapping
    public String list(Model model,
                       @RequestParam(defaultValue = "0") int page) {

        int pageSize = 5; // 한 페이지당 5개 게시글
        var postPage = postService.findAllPaged(page, pageSize);

        model.addAttribute("posts", postPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", postPage.getTotalPages());

        return "post-list";
    }

    // 상세보기
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model,
                         @AuthenticationPrincipal UserDetails userDetails) {
        Post post = postService.findById(id);
        if (post == null) return "redirect:/posts";

        model.addAttribute("post", post);
        model.addAttribute("comments", commentService.getCommentsByPostId(id));
        model.addAttribute("currentUser", userDetails != null ? userDetails.getUsername() : null);
        return "post-detail";
    }

    // 새 글 작성 폼
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("post", new Post());
        return "post-form";
    }

    // 새 글 저장
    @PostMapping("/new")
    public String createPost(Post post,
                             @RequestParam(value = "images", required = false) List<MultipartFile> images,
                             @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        if (userDetails != null)
            post.setAuthor(userDetails.getUsername());

        postService.savePostWithImages(post, images);
        return "redirect:/posts";
    }

    // 수정 폼
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model,
                           @AuthenticationPrincipal UserDetails userDetails) {
        Post post = postService.findById(id);
        if (post == null) return "redirect:/posts";

        // 작성자만 접근 가능
        if (userDetails == null || !userDetails.getUsername().equals(post.getAuthor())) {
            return "redirect:/posts/" + id + "?error=unauthorized";
        }

        model.addAttribute("post", post);
        return "post-edit";
    }

    // 수정 저장
    @PostMapping("/{id}/edit")
    public String updatePost(@PathVariable Long id, Post post,
                             @RequestParam(value = "images", required = false) List<MultipartFile> images,
                             @AuthenticationPrincipal UserDetails userDetails) throws IOException {

        Post existing = postService.findById(id);
        if (existing == null) return "redirect:/posts";
        if (userDetails == null || !existing.getAuthor().equals(userDetails.getUsername())) {
            return "redirect:/posts/" + id + "?error=unauthorized";
        }

        // 제목/내용 변경
        existing.setTitle(post.getTitle());
        existing.setContent(post.getContent());

        // ✅ 기존 이미지 clear 후 새 이미지 저장
        postService.updatePostWithNewImages(existing, images);

        return "redirect:/posts/" + id;
    }

    // 삭제
    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id,
                             @AuthenticationPrincipal UserDetails userDetails) {
        Post post = postService.findById(id);
        if (post == null) return "redirect:/posts";
        if (userDetails == null || !userDetails.getUsername().equals(post.getAuthor())) {
            return "redirect:/posts/" + id + "?error=unauthorized";
        }

        postService.delete(id);
        return "redirect:/posts";
    }
}
