package com.du.quiz1.mapper;

import com.du.quiz1.domain.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MenuMapper {

    // 가독성/안전성을 위해 컬럼 명시 + 별칭
    @Select("""
      SELECT id, name, brand, price, category,
             spicy_level AS spicyLevel, available
      FROM menu
      ORDER BY id DESC
    """)
    List<Menu> findAll();

    @Select("""
      SELECT id, name, brand, price, category,
             spicy_level AS spicyLevel, available
      FROM menu
      WHERE id = #{id}
    """)
    Menu findById(Long id);

    @Insert("""
      INSERT INTO menu (name, brand, price, category, spicy_level, available)
      VALUES (#{name}, #{brand}, #{price}, #{category}, #{spicyLevel}, #{available})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Menu menu);

    @Update("""
      UPDATE menu
      SET name = #{name},
          brand = #{brand},
          price = #{price},
          category = #{category},
          spicy_level = #{spicyLevel},
          available = #{available}
      WHERE id = #{id}
    """)
    int update(Menu menu);

    @Delete("DELETE FROM menu WHERE id = #{id}")
    int delete(Long id);
}
