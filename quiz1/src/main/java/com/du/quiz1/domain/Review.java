package com.du.quiz1.domain;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Review {
    private Long id;
    private Long menuId;
    private String author;
    private Integer rating;     // 1~5
    private String content;
    private LocalDateTime createdAt;
}
