package com.du.quiz1.domain;

import lombok.Data;

@Data
public class Menu {
    private Long id;
    private String name;
    private String brand;   // ← 추가
    private int price;
    private String category;
    private Integer spicyLevel;
    private boolean available;
    // getter/setter
}

