package com.du.jpa0924.repository;

import com.du.jpa0924.entity.MyData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyDataRepository extends JpaRepository<MyData, Long> {

}
