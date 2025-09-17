package com.du.bagunee0917;

import com.du.bagunee0917.dao.ProductMapper;
import com.du.bagunee0917.domain.Product;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


//@SpringBootTest
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class Bagunee0917ApplicationTests {

    @Autowired
    private ProductMapper productMapper;

    @Test
    void contextLoads() {
        List<Product> products = productMapper.findAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }

}
