use hotel;

insert into Guests(id_guest, name, last_name)
values(1, 'Konstantin', 'Kinchev'),(2,'Andrey','Makarevich'),
(3, 'Valeriy', 'Kipelov');

insert into Rooms(id_room, copaciti, stars, price)
values (1, 3, '2', 12.5), (2, 4, '2', 10.0), (3, 2, '4', 18.4);

insert into Services(id_service, name, price)
values (1,'Bar',5.0), (2, 'swiming_pool', 8.0), (3, 'Cleaning', 3.5);

insert into History(id, id_room, id_guest, id_service, date_of_arival, evict_date)
values (1, 1, 1, 1, "2007.07.21", "2007.07.24"),(2,2,2,1,"2008.03.20","2008.03.27");