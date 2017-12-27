package com.hotel.ui.builder;

import java.util.Scanner;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.ui.action.MenuItem;
import com.hotel.ui.menu.Menu;
import com.hotel.utils.Printer;

public class Builder {
	private Menu rootMenu;

	public Menu buildMenu() {

		Hotel hotel = Hotel.getInstance();
		Menu menu = new Menu("Menu");
		Menu guest = new Menu("Guest menu");
		Menu room = new Menu("Room menu");
		Menu option = new Menu("Option menu");
		guestMenu(hotel, guest);
		return menu;
	}

	private void guestMenu(Hotel hotel, Menu guest) {
		guest.addItem(new MenuItem("Add guest", rootMenu, new IAction() {

			@Override
			public void execute() {
				Scanner sc = new Scanner(System.in);
				String name = sc.nextLine();
				String lastName = sc.nextLine();
				hotel.addGuest(name, lastName);
				sc.close();
			}

		}));
		guest.addItem(new MenuItem("Number of guests", rootMenu, new IAction() {

			@Override
			public void execute() {
				Printer.println(hotel.printNumberOfGuests());
			}

		}));
		guest.addItem(new MenuItem("Guest list", rootMenu, new IAction() {

			@Override
			public void execute() {
				hotel.printGuestList();
			}

		}));

	}

	public Menu getRootMenu() {
		return rootMenu;
	}

}
