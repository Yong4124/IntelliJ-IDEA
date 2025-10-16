package com.du.myproject1.controller;

import com.du.myproject1.domain.Comment;
import com.du.myproject1.domain.Post;
import com.du.myproject1.service.CommentService;
import com.du.myproject1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    @PostMapping("/posts/{postId}/comments")
    public String addComment(@PathVariable Long postId, Comment comment,
                             @AuthenticationPrincipal UserDetails userDetails) {
        Post post = postService.findById(postId);
        if (post == null) return "redirect:/posts";

        comment.setPost(post);
        comment.setAuthor(userDetails.getUsername());
        commentService.save(comment);

        return "redirect:/posts/" + postId;
    }

    @PostMapping("/comments/{commentId}/delete")
    public String deleteComment(@PathVariable Long commentId, Long postId) {
        commentService.delete(commentId);
        return "redirect:/posts/" + postId;
    }
}
