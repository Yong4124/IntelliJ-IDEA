package com.du.jpa0924;

import com.du.jpa0924.entity.MyData;
import com.du.jpa0924.repository.MyDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class Jpa0924ApplicationTests {

    @Autowired
    private MyDataRepository myDataRepository;

    @Test
    void contextLoads_insert() {   // insert
        MyData myData = MyData.builder()
                .name("홍길동")
                .age(20)
                .email("hong@korea.com")
                .memo("연습입니다.")
                .build();

        myDataRepository.save(myData); // INSERT 실행
    }

    @Test
    void contextLoads_findById() {
        Optional<MyData> myData = myDataRepository.findById(1L);
        myData.ifPresent(d -> System.out.println(d.getName())); // 안전하게 출력
    }

    @Test
    void contextLoads_findAll() {
        List<MyData> list = myDataRepository.findAll();
        list.forEach(d -> System.out.println(d.getName()));
    }
}
