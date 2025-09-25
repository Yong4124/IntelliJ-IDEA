package com.du.quiz1.service;

import com.du.quiz1.domain.Review;
import com.du.quiz1.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;

    public List<Review> listByMenu(Long menuId) {
        return reviewMapper.findByMenuId(menuId);
    }

    public double avgRating(Long menuId) {
        Double avg = reviewMapper.avgRating(menuId);
        return avg == null ? 0.0 : avg;
    }

    @Transactional
    public Long create(Review review) {
        if (review.getRating() == null || review.getRating() < 1 || review.getRating() > 5) {
            throw new IllegalArgumentException("rating은 1~5 범위여야 합니다.");
        }
        reviewMapper.insert(review);
        return review.getId();
    }

    @Transactional
    public void delete(Long id) {
        reviewMapper.delete(id);
    }
}
