create table if not exists bike_shop.bike_elements(
	element_id int not null auto_increment primary key,
    category_id int not null,
	model varchar(40) not null,
	price double not null,
	specification varchar(40),
	imgPath varchar(40),
    foreign key (category_id) references bike_shop.categories(category_id)
);

insert into bike_shop.bike_elements values
(null,1, 'frame1', 100, ' ', 'frame1'),
(null,1,'frame2', 100, ' ', 'frame2'),
(null,1,'frame3', 100, ' ', 'frame3'),
(null,1,'frame4', 100, ' ', 'frame4'),
(null,1,'frame5', 100, ' ', 'frame5'),

(null,2, 'derailleur1', 100, ' ', 'derailleur1'),
(null,2,'derailleur2', 100, ' ', 'derailleur2'),
(null,2,'derailleur3', 100, ' ', 'derailleur3'),
(null,2,'derailleur4', 100, ' ', 'derailleur4'),
(null,2,'derailleur5', 100, ' ', 'derailleur5'),

(null,3, 'brake1', 100, ' ', 'brake1'),
(null,3,'brake2', 100, ' ', 'brake2'),
(null,3,'brake3', 100, ' ', 'brake3'),
(null,3,'brake4', 100, ' ', 'brake4'),
(null,3,'brake5', 100, ' ', 'brake5'),

(null,4, 'handlebar1', 100, ' ', 'handlebar1'),
(null,4,'handlebar2', 100, ' ', 'handlebar2'),
(null,4,'handlebar3', 100, ' ', 'handlebar3'),
(null,4,'handlebar4', 100, ' ', 'handlebar4'),
(null,4,'handlebar5', 100, ' ', 'handlebar5'),

(null,5, 'pedal1', 100, ' ', 'pedal1'),
(null,5,'pedal2', 100, ' ', 'pedal2'),
(null,5,'pedal3', 100, ' ', 'pedal3'),
(null,5,'pedal4', 100, ' ', 'pedal4'),
(null,5,'pedal5', 100, ' ', 'pedal5'),

(null,6, 'saddle1', 100, ' ', 'saddle1'),
(null,6,'saddle2', 100, ' ', 'saddle2'),
(null,6,'saddle3', 100, ' ', 'saddle3'),
(null,6,'saddle4', 100, ' ', 'saddle4'),
(null,6,'saddle5', 100, ' ', 'saddle5'),

(null,7, 'wheel1', 100, ' ', 'wheel1'),
(null,7,'wheel2', 100, ' ', 'wheel2'),
(null,7,'wheel3', 100, ' ', 'wheel3'),
(null,7,'wheel4', 100, ' ', 'wheel4'),
(null,7,'wheel5', 100, ' ', 'wheel5')