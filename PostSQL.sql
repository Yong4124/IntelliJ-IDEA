select * from post order by id desc limit 10 offset 30;


create database if not exists board character set utf8mb4 collate utf8mb4_unicode_ci;

use board;

create table post(
	id bigint auto_increment primary key,
    title varchar(255) not null,
    content text not null,
    writer varchar(100) not null
);

select * from post;