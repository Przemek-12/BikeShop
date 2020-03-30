create table if not exists bike_shop.orders(
	order_id int primary key not null,
    user_id int not null,
    bike_description varchar(300) not null,
    price double not null,
    orderSubmitTime datetime not null,
    foreign key (user_id) references bike_shop.users(user_id)
);

create table if not exists bike_shop.order_items(
	order_item_id int primary key not null auto_increment,
    order_id int not null,
    element_id int not null,
    foreign key (order_id) references bike_shop.orders(order_id),
    foreign key (element_id) references bike_shop.bike_elements(element_id)
);

create table if not exists bike_shop.orderId_generator(
	orderId_seq bigint(20)
);

create table if not exists bike_shop.orderId_seq(
	next_val bigint(20)
);