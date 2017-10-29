package com.project.fasad;

import java.util.Arrays;

import com.project.been.Guest;
import com.project.been.Room;
import com.project.service.*;

public class Hotel {
	private RoomService roomService = new RoomService();
	// private HistoryService historyService=new HistoryService();
	private GuestService guestService = new GuestService();
	// private OptionService optionService=new OptionService();

	public void addGuest(Guest guest) {
		guestService.addGuest(guest);
	}

	public void printAllGuests() {
		guestService.printGuestList(guestService.getGuest());
	}

	public void printNumberOfGuests() {
		Printer.println(guestService.getNumberOfGuests());
	}

	public void settleGuestInRoom(Integer guestId, Room room) {
		guestService.settleGuestInRoom(guestId, room);

	}

	public void sortedGuestByName() {
		Arrays.sort(guestService.getGuest(), new SortedByName());
		Printer.println("   Guest list sorted by name:");
		for (int i = 0; i < guestService.getGuest().length; i++) {
			if (guestService.getGuest()[i] != null) {
				Printer.println(guestService.getGuest()[i].toString());
			}
		}
	}

	// ------------------------------------------------------------------
	public void printAllRooms() {
		roomService.printRoomList();
	}

	public void printFreeRooms() {
		roomService.printFreeRoomList();
	}

	// public void printRoom(Integer roomNumber) {
	// roomService.
	// }
	public void printNumberOfRooms() {
		Printer.println(roomService.getNumberOfRooms());
	}

	public void printNumberOfFreeRooms() {
		Printer.println(roomService.getNumberOfFreeRooms());
	}

	public void addRoom(Room room) {
		roomService.addRoom(room);
	}

	public void sortedRoomsByPrice() {
		Arrays.sort(roomService.getRoom(), new SortedRoomByPrice());
		Printer.println("   Room list sorted by price:");
		for (int i = 0; i < roomService.getRoom().length; i++) {
			if (roomService.getRoom()[i] != null) {
				Printer.println(roomService.getRoom()[i].toString());
			}
		}
	}

	public void sortedRoomsByCopaciti() {
		Arrays.sort(roomService.getRoom(), new SortedByCopacity());
		Printer.println("   Room list sorted by copacity:");
		for (int i = 0; i < roomService.getRoom().length; i++) {
			if (roomService.getRoom()[i] != null) {
				Printer.println(roomService.getRoom()[i].toString());
			}
		}
	}

	public void sortedRoomsByStars() {
		Arrays.sort(roomService.getRoom(), new SortedByStars());
		Printer.println("   Room list sorted by stars:");
		for (int i = 0; i < roomService.getRoom().length; i++) {
			if (roomService.getRoom()[i] != null) {
				Printer.println(roomService.getRoom()[i].toString());
			}
		}
	}

	public void guestStart() {
		guestService.settleGuestInRoom(0, roomService.getROOM().getRoomByNumber(401));
		guestService.settleGuestInRoom(1, roomService.getROOM().getRoomByNumber(402));
		guestService.printGuestList(guestService.getGUEST().getGuests());

	}

	public void roomStart() {
		roomService.printRoomList();
		roomService.printFreeRoomList();
		roomService.getNumberOfRooms();
	}
}

// }
