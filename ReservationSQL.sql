create database reservation_db character set utf8mb4 collate utf8mb4_general_ci;

use reservation_db;

create table services (
	id bigint auto_increment primary key,
    name varchar(100) not null,
    description varchar(255),
    price int not null
);

create table reservations(
	id bigint auto_increment primary key,
    customer_name varchar(100) not null,
    reservation_date date not null
);

create table reservation_items (
	id bigint auto_increment primary key,
    reservation_id bigint not null,
    service_id bigint not null,
    quantity int not null,
    foreign key (reservation_id) references reservations(id) on delete cascade,
    foreign key (service_id) references services(id)
);

USE reservation_db;

-- 서비스 (서비스명, 설명, 가격)
INSERT INTO services(name, description, price) VALUES
('스파 이용권', '1시간 스파 이용', 50000),
('요가 클래스', '초급 요가 수업', 30000),
('마사지', '전신 마사지 60분', 70000),
('피트니스 PT', '1회 개인 트레이닝', 60000);

-- 예약 (고객명, 예약일)
INSERT INTO reservations(customer_name, reservation_date) VALUES
('홍길동', '2025-09-20'),
('김철수', '2025-09-21');

-- 예약 항목 (reservation_id, service_id, quantity)
-- 홍길동 예약 (id=1)
INSERT INTO reservation_items(reservation_id, service_id, quantity) VALUES
(1, 1, 2),  -- 스파 이용권 2명
(1, 2, 1);  -- 요가 클래스 1명

-- 김철수 예약 (id=2)
INSERT INTO reservation_items(reservation_id, service_id, quantity) VALUES
(2, 3, 1),  -- 마사지 1명
(2, 4, 3);  -- 피트니스 PT 3명

select * from services;
select * from services where id = 2;
select * from reservations order by reservation_date desc;
select * from reservation_items;

select r.id as reservation_id, r.customer_name, r.reservation_date,
	   ri.id as item_id, ri.quantity,
       s.id as service_id, s.name as service_name, s.price
from reservations r
join reservation_items ri on r.id = ri.reservation_id
join services s on ri.service_id = s.id
where r.id = 1;

select * from reservations where customer_name = '홍길동';

select r.id, r.customer_name, r.reservation_date, sum(ri.quantity * s.price) as total_amount
from reservations r
join reservation_items ri on r.id = ri.reservation_id
join services s on ri.service_id = s.id
group by r.id, r.customer_name, r.reservation_date;