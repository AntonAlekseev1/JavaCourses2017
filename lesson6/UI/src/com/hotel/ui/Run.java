package com.hotel.ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.hotel.fasad.Hotel;
import com.hotel.ui.menucontroller.MenuController;

public class Run {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Hotel.getInstance().demarshalingFrom();;
		MenuController menu = new MenuController();
		menu.run();
		Hotel.getInstance().marshalingTo();
	}

}
