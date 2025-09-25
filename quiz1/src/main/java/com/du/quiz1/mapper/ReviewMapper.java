package com.du.quiz1.mapper;

import com.du.quiz1.domain.Review;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ReviewMapper {

    @Select("""
      SELECT id, menu_id AS menuId, author, rating, content, created_at AS createdAt
      FROM review
      WHERE menu_id = #{menuId}
      ORDER BY id DESC
    """)
    List<Review> findByMenuId(Long menuId);

    @Select("""
      SELECT COALESCE(AVG(rating),0) FROM review WHERE menu_id = #{menuId}
    """)
    Double avgRating(Long menuId);

    @Insert("""
      INSERT INTO review(menu_id, author, rating, content)
      VALUES (#{menuId}, #{author}, #{rating}, #{content})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Review review);

    @Delete("DELETE FROM review WHERE id = #{id}")
    int delete(Long id);
}
