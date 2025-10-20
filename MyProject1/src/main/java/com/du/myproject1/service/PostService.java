package com.du.myproject1.service;

import com.du.myproject1.domain.Post;
import com.du.myproject1.domain.PostImagePath;
import com.du.myproject1.repository.CommentRepository;
import com.du.myproject1.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    // ✅ 신규 저장 (문제 없음)
    @Transactional
    public void savePostWithImages(Post post, List<MultipartFile> images) throws IOException {
        List<PostImagePath> imagePaths = uploadImages(images, post);
        post.setImagePaths(imagePaths);
        postRepository.save(post);
    }

    // ✅ 수정 시 컬렉션 안전하게 덮어쓰기
    @Transactional
    public void updatePostWithNewImages(Post post, List<MultipartFile> images) throws IOException {
        if (post.getImagePaths() != null) {
            post.getImagePaths().clear(); // 기존 이미지 삭제
        }

        List<PostImagePath> newPaths = uploadImages(images, post);
        if (newPaths != null && !newPaths.isEmpty()) {
            post.getImagePaths().addAll(newPaths); // ❗ set() 대신 addAll()
        }

        postRepository.save(post);
    }

    // ✅ 공통 이미지 업로드 로직
    private List<PostImagePath> uploadImages(List<MultipartFile> images, Post post) throws IOException {
        List<PostImagePath> imagePaths = new ArrayList<>();

        if (images != null && !images.isEmpty()) {
            String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            for (MultipartFile file : images) {
                if (!file.isEmpty()) {
                    String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
                    File dest = new File(uploadDir + filename);
                    file.transferTo(dest);

                    imagePaths.add(new PostImagePath("/uploads/" + filename, post));
                }
            }
        }
        return imagePaths;
    }

    // 기존 PostService 내부에 추가
    public Page<Post> findAllPaged(int page, int size) {
        return postRepository.findAll(
                PageRequest.of(page, size, Sort.by("createdAt").descending())
        );
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) return;
        commentRepository.deleteByPostId(id);
        postRepository.delete(post);
    }
}
