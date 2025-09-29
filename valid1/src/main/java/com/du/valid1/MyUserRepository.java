package com.du.valid1;

import com.du.valid1.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<엔티티, PK 타입>
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
}
