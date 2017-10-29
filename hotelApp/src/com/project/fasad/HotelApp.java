package com.project.fasad;

import com.project.been.Guest;
import com.project.been.Room;

public class HotelApp {
	public static void main(String[] args) {

		Room room = new Room(401, 2, 3, 21.00);
		Room room1 = new Room(402, 3, 2, 18.00);
		Room room2 = new Room(403, 1, 5, 32.00);
		Room room3 = new Room(404, 5, 1, 14.00);

		Guest guest = new Guest("Vasia", "Petin", 1);
		Guest guest1 = new Guest("Dasha", "Ersh", 2);
		Guest guest2 = new Guest("Ser", "Jhon", 3);

		Hotel hotel = new Hotel();

		hotel.addGuest(guest);
		hotel.addGuest(guest1);
		hotel.addGuest(guest2);

		hotel.addRoom(room);
		hotel.addRoom(room1);
		hotel.addRoom(room2);
		hotel.addRoom(room3);

		hotel.settleGuestInRoom(1, room);
		hotel.settleGuestInRoom(2, room1);

		hotel.printNumberOfRooms();
		hotel.printAllRooms();
		hotel.printNumberOfFreeRooms();
		hotel.printFreeRooms();
		hotel.printNumberOfGuests();
		hotel.printAllGuests();

		hotel.sortedRoomsByPrice();
		hotel.sortedRoomsByCopaciti();
		hotel.sortedRoomsByStars();
		hotel.sortedGuestByName();

	}

}
