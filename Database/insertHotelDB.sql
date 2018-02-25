use hotel;

insert into Guests(name, last_name)
values('Konstantin', 'Kinchev'),('Andrey','Makarevich'),
('Valeriy', 'Kipelov');

insert into Rooms(number, copacity, stars, price,status, is_free)
values (101, 3, 2, 12.5,'SERVICED', false), (102, 4,2, 10.0,'SERVICED', false), (220, 2,4, 18.4,'OPEN', true);

insert into Options(name, price)
values ('Bar',5.0), ('swiming_pool', 8.0), ('Cleaning', 3.5);

insert into History(id_room, id_guest, date_of_arival, evict_date)
values ( 1, 1, "2007.07.21", "2007.07.24"),(2,2,"2008.03.20","2008.03.27");

insert into Options_history(id_option, id_history)
values (1,1), (3,2);