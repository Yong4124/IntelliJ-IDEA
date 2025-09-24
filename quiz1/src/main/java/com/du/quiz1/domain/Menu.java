package com.du.quiz1.domain;

import lombok.Data;

@Data
public class Menu {
    private Long id;
    private String name;
    private Integer price;     // 원 단위
    private String category;   // FRIED/SAUCED/SIDE/BEVERAGE 등
    private Integer spicyLevel; // 0~5
    private Boolean available; // 판매중 여부
}
