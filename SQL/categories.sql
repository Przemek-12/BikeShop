create table if not exists bike_shop.categories(
	category_id int not null auto_increment primary key,
    category_name varchar(40)
);

insert into bike_shop.categories values
(null, 'brakes'),
(null, 'derailleurs'),
(null, 'frames'),
(null, 'handlebars'),
(null, 'pedals'),
(null, 'saddles'),
(null, 'wheels');
