package com.hotel.fasad;

import com.hotel.been.Guest;
import com.hotel.been.Room;
import com.hotel.been.RoomStatus;
import com.hotel.been.Option;
import java.util.GregorianCalendar;
    
public class HotelApp {
	
	public static final Hotel hotel = new Hotel();
	
	private static void createEntities() {
		hotel.addRoom(new Room( 2, 3, 21.00));
		hotel.addRoom(new Room( 3, 2, 18.00));
		hotel.addRoom(new Room( 1, 5, 32.00));
		hotel.addRoom(new Room( 5, 1, 14.00));
		
		hotel.addGuest(new Guest("Vasia", "Petin"));
		hotel.addGuest( new Guest("Dasha", "Ersh"));
		hotel.addGuest(new Guest("Ser", "Jhon"));
		
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
		hotel.printFreeRoomsOnDate(new GregorianCalendar(2102,01,13));

		hotel.writeInFile();
		hotel.readFromFile();
		
	}
	


}