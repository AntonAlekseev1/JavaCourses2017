package com.hotel.ui;


import java.util.GregorianCalendar;

import com.hotel.been.Guest;
import com.hotel.been.Option;
import com.hotel.been.Room;
import com.hotel.api.been.RoomStatus;
import com.hotel.fasad.Hotel;
    
public class StartTest {
	
	public static final Hotel hotel = new Hotel();
	
	private static void createEntities() {
	
		hotel.addRoom(new Room( 2, 3, 21.00));
		hotel.addRoom(new Room( 3, 2, 18.00));
		hotel.addRoom(new Room( 1, 5, 32.00));
		hotel.addRoom(new Room( 5, 1, 14.00));
		
		hotel.addGuest("vas","vas");
	//	hotel.addGuest( new Guest("Dasha", "Ersh"));
	//	hotel.addGuest(new Guest("Ser", "Jhon"));
		
		hotel.addOption(new Option("Bar", 10.0));
		hotel.addOption(new Option("swimming pool", 15.3));
		hotel.addOption(new Option("phone", 1.2));
		
	}
	
	
	public static void main(String[] args) {
		 
		createEntities();


		hotel.settleGuestInRoom(1, 1, new GregorianCalendar(2102,01,01), new GregorianCalendar(2102,01,11));
		hotel.settleGuestInRoom(2, 2, new GregorianCalendar(2102,01,01), new GregorianCalendar(2102,01,14));
		hotel.chengeRoomStatus(1,RoomStatus.CLOSE);
		hotel.chengePriceOfRoom(2, 20.2);
		
		hotel.settleGuestInRoom(2, 1, new GregorianCalendar(2103,03,01), new GregorianCalendar(2103,03,11));
		hotel.evictGuestFromRoom(2, 1);
    	hotel.settleGuestInRoom(3, 1, new GregorianCalendar(2104,04,15), new GregorianCalendar(2104,04,17));

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
		
		hotel.printOptionList();
		hotel.printGuestsRooms();
		hotel.addOptionToGuest(1, 1);
		hotel.addOptionToGuest(2, 1);
		hotel.printGuestOptions(1);
		hotel.printRoonById(1);
		hotel.printOptionById(1);
		hotel.getTotalPayment(1);
		hotel.printFreeRoomsOnDate(new GregorianCalendar(2104,04,16));
		hotel.printLastVisiors(1);
	//	hotel.printLastVisiors(4);
		
		

		hotel.writeInFile();
		hotel.readFromFile();
		
	}
	


}