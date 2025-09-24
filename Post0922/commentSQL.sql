create table comment (
   id bigint auto_increment primary key,
   post_id bigint not null,
   writer varchar(100) not null,
   content text not null,
   created_at datetime default current_timestamp,
   foreign key (post_id) references post(id) on delete cascade
);

set global time_zone = '+09:00';
set time_zone = '+09:00';
select now();

select * from comment;
