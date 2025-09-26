package com.du.jpa0926;

import com.du.jpa0926.entity.Post;
import com.du.jpa0926.repository.PostRepository;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.persistence.Column;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


@SpringBootTest
class Jpa0926ApplicationTests {
    @Autowired
    private PostRepository postRepository;



    @Test
    void contextLoads() {
        Post post = Post.builder()
                .title("안녕1")
                .content("안녕하세요1")
                .author("홍길동1")
                .build();
        postRepository.save(post);

    }

    @Test
    void findAll() {
        Optional<Post> posts = postRepository.findById(1L);
        System.out.println(posts);
    }

    @Test
    void findAllPosts() {
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            System.out.println(post);
        }
    }

    @Test
    void updatePost() {
        Post post = postRepository.findById(2L).get();
        post.setAuthor("김하나");
        post.setTitle("감사합니다");
        postRepository.save(post);
    }

    @Test
    void deletePost() {
        postRepository.deleteById(1L);
    }

}
