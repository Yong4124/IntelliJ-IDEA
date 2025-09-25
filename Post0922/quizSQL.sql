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

ALTER TABLE menu ADD COLUMN brand VARCHAR(100);


select * from menu;

-- 리뷰 테이블
CREATE TABLE review (
  id         BIGINT PRIMARY KEY AUTO_INCREMENT,
  menu_id    BIGINT NOT NULL,
  author     VARCHAR(50) NOT NULL,
  rating     INT NOT NULL,          -- 1~5
  content    TEXT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_review_menu
    FOREIGN KEY (menu_id) REFERENCES menu(id)
    ON DELETE CASCADE
);

drop table menu;

