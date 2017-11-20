package com.hotel.ui.builder;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.Exit;
import com.hotel.ui.action.MenuItem;
import com.hotel.ui.actions.guest.AddGuest;
import com.hotel.ui.actions.guest.AddOptionToGuest;
import com.hotel.ui.actions.guest.AllGuests;
import com.hotel.ui.actions.guest.GuestOptions;
import com.hotel.ui.actions.guest.NumberOfGuests;
import com.hotel.ui.actions.guest.SortGuestsByName;
import com.hotel.ui.actions.guest.TotalPayment;
import com.hotel.ui.actions.history.PrintFreeRoomOnDate;
import com.hotel.ui.actions.history.SettleGuestInRoom;
import com.hotel.ui.actions.option.AddOption;
import com.hotel.ui.actions.option.AllOptions;
import com.hotel.ui.actions.option.OptionById;
import com.hotel.ui.actions.room.AddRoom;
import com.hotel.ui.actions.room.AllRooms;
import com.hotel.ui.actions.room.ChangeRoomPrice;
import com.hotel.ui.actions.room.ChangeRoomStatus;
import com.hotel.ui.actions.room.Clone;
import com.hotel.ui.actions.room.FreeRooms;
import com.hotel.ui.actions.room.LastGuests;
import com.hotel.ui.actions.room.NumberOfFreeRooms;
import com.hotel.ui.actions.room.NumberOfRooms;
import com.hotel.ui.actions.room.RoomById;
import com.hotel.ui.actions.room.SortRoomsByCopacity;
import com.hotel.ui.actions.room.SortRoomsByPrice;
import com.hotel.ui.actions.room.SortRoomsByStars;
import com.hotel.ui.menu.Menu;

public class Builder {
	private Menu rootMenu = new Menu("root");

	public Menu buildMenu() {

		Hotel hotel = Hotel.getInstance();
		Menu menu = new Menu("Menu");
		Menu guest = new Menu("Guest menu");
		Menu room = new Menu("Room menu");
		Menu option = new Menu("Option menu");
		Menu history = new Menu("History menu");

		rootMenuInit(room, guest, option, history);
		guestMenu(hotel, guest);
		roomMenu(hotel, room);
		optionMenu(hotel, option);
		historyMenu(hotel, history);
		return menu;
	}

	private void guestMenu(Hotel hotel, Menu guest) {
		guest.addItem(new MenuItem("Exit", rootMenu, new Exit()));
		guest.addItem(new MenuItem("Add guest", rootMenu, new AddGuest()));
		guest.addItem(new MenuItem("Number of guests", rootMenu, new NumberOfGuests()));
		guest.addItem(new MenuItem("Guest list", rootMenu, new AllGuests()));
		guest.addItem(new MenuItem("Guest options", rootMenu, new GuestOptions()));
		guest.addItem(new MenuItem("Add option to guest", rootMenu, new AddOptionToGuest()));
		guest.addItem(new MenuItem("Sort guests by name", rootMenu, new SortGuestsByName()));
		guest.addItem(new MenuItem("Total payment", rootMenu, new TotalPayment()));

	}

	private void roomMenu(Hotel hotel, Menu room) {
		room.addItem(new MenuItem("Exit", rootMenu, new Exit()));
		room.addItem(new MenuItem("Add room", rootMenu, new AddRoom()));
		room.addItem(new MenuItem("Clone room", rootMenu, new Clone()));
		room.addItem(new MenuItem("Print all rooms", rootMenu, new AllRooms()));
		room.addItem(new MenuItem("Change room Price", rootMenu, new ChangeRoomPrice()));
		room.addItem(new MenuItem("Change room status", rootMenu, new ChangeRoomStatus()));
		room.addItem(new MenuItem("Print free rooms", rootMenu, new FreeRooms()));
		room.addItem(new MenuItem("Number of free roms", rootMenu, new NumberOfFreeRooms()));
		room.addItem(new MenuItem("Number of roms", rootMenu, new NumberOfRooms()));
		room.addItem(new MenuItem("Show room by id", rootMenu, new RoomById()));
		room.addItem(new MenuItem("Show last guests of the room ", rootMenu, new LastGuests()));
		room.addItem(new MenuItem("Show room list sorted by copacity", rootMenu, new SortRoomsByCopacity()));
		room.addItem(new MenuItem("Show room list sorted by stars", rootMenu, new SortRoomsByStars()));
		room.addItem(new MenuItem("Show room list sorted by price", rootMenu, new SortRoomsByPrice()));
	}

	private void optionMenu(Hotel hotel, Menu option) {
		option.addItem(new MenuItem("Exit", rootMenu, new Exit()));
		option.addItem(new MenuItem("Add option", rootMenu, new AddOption()));
		option.addItem(new MenuItem("Print all options", rootMenu, new AllOptions()));
		option.addItem(new MenuItem("Option by id", rootMenu, new OptionById()));
	}

	private void historyMenu(Hotel hotel, Menu history) {
		history.addItem(new MenuItem("Exit", rootMenu, new Exit()));
		history.addItem(new MenuItem("Setle guest in room", rootMenu, new SettleGuestInRoom()));
		history.addItem(new MenuItem("Print free rooms on date", rootMenu, new PrintFreeRoomOnDate()));
	}

	private void rootMenuInit(final Menu room, final Menu guest, final Menu option,final  Menu history) {

	   rootMenu.addItem(new MenuItem("Exit", null, new Exit()));
	   rootMenu.addItem(new MenuItem("Guest", guest, new Exit()));
		rootMenu.addItem(new MenuItem("Room", room, new Exit()));
        rootMenu.addItem(new MenuItem("Option", option, new Exit()));
        rootMenu.addItem(new MenuItem("History", history, new Exit()));
       
    }

	public Menu getRootMenu() {
		return rootMenu;
	}

}
