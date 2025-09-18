package com.du.reservation0918.dao;


import com.du.reservation0918.model.MyService;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ServiceMapper {
    @Select("SELECT * FROM services ORDER BY id DESC")
    List<MyService> findAll();

    @Insert("INSERT INTO services(name, description, price) VALUES(#{name}, #{description}, #{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(MyService service);

    @Delete("DELETE FROM services WHERE id = #{id}")
    void delete(Long id);
}
