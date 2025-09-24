package com.du.quiz1.mapper;


import com.du.quiz1.domain.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MenuMapper {

    @Select("SELECT * FROM menu ORDER BY id DESC")
    List<Menu> findAll();

    @Select("SELECT * FROM menu WHERE id = #{id}")
    Menu findById(Long id);

    @Insert("""
        INSERT INTO menu(name, price, category, spicy_level, available)
        VALUES (#{name}, #{price}, #{category}, #{spicyLevel}, #{available})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Menu menu);

    @Update("""
        UPDATE menu
        SET name=#{name}, price=#{price}, category=#{category},
            spicy_level=#{spicyLevel}, available=#{available}
        WHERE id=#{id}
    """)
    void update(Menu menu);

    @Delete("DELETE FROM menu WHERE id = #{id}")
    void delete(Long id);
}