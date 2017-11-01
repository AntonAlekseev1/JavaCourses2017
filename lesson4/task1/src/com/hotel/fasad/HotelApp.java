package com.hotel.fasad;

import com.hotel.been.Guest;
import com.hotel.been.Room;
import com.hotel.been.RoomStatus;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.danco.training.TextFileWorker;

    
public class HotelApp {
	
	public static final Hotel hotel = new Hotel();
	
	private static void writeInFile() {
		hotel.addRoom(new Room(401, 2, 3, 21.00));
		hotel.addRoom(new Room(402, 3, 2, 18.00));
		hotel.addRoom(new Room(403, 1, 5, 32.00));
		hotel.addRoom(new Room(404, 5, 1, 14.00));
		
		hotel.addGuest(new Guest("Vasia", "Petin", 1));
		hotel.addGuest( new Guest("Dasha", "Ersh", 2));
		hotel.addGuest(new Guest("Ser", "Jhon", 3));
	}
	
	
	public static void main(String[] args) {
		
		String pathToGuests;
		String pathToRooms;

		
		if (args.length > 0) {
			pathToGuests = args[0];
			pathToRooms = args[1];

		}
		else {
			pathToGuests="D:\\1\\guests.txt";
		    pathToRooms="D:\\1\\rooms.txt";

		}
//		writeInFile();
		
//		hotel.writeInFile(pathToGuests, pathToRooms);
		
		hotel.readFromFile(pathToGuests, pathToRooms);



		hotel.settleGuestInRoom(1, 1, new GregorianCalendar(2102,01,01), new GregorianCalendar(2102,01,11));
		hotel.settleGuestInRoom(2, 2, new GregorianCalendar(2102,01,01), new GregorianCalendar(2102,01,11));
		hotel.chengeRoomStatus(1,RoomStatus.CLOSE);
		hotel.chengePriceOfRoom(2, 20.2);

		hotel.printNumberOfRooms();
		hotel.printRoomList();
		hotel.printNumberOfFreeRooms();
		hotel.printFreeRoomsList();
		hotel.printNumberOfGuests();
		hotel.printGuestList();

		hotel.sortedRoomsByPrice();
		hotel.sortedRoomsByCopaciti();
		hotel.sortedRoomsByStars();
		hotel.sortedGuestByName();

		
	}
	


}
