CREATE DATABASE IF NOT EXISTS chicken CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE chicken;

CREATE TABLE IF NOT EXISTS menu (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  price INT NOT NULL,
  category VARCHAR(50) NOT NULL,      -- 예: FRIED, SAUCED, SIDE, BEVERAGE
  spicy_level INT DEFAULT 0,          -- 0~5 정도로 사용
  available BOOLEAN DEFAULT TRUE      -- 판매 가능 여부
);

select * from menu;

DROP TABLE menu;
