create table products (
	id BIGINT auto_increment primary key,
	name varchar(100),
    price int
);

create table cart_items (
	id bigint auto_increment primary key,
    product_id bigint,
    quantity int
);

insert into products(name, price) values ('Keyboard', 30000), ('Mouse', 15000), ('Monitor', 20000);

select * from products;

select * from cart_items; 

SELECT c.*, p.id as product_id, p．name, p.price FROM cart_items c JOIN products p ON c.product_id = p.id;