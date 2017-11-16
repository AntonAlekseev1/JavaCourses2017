package com.hotel.ui;

import com.hotel.fasad.Hotel;
import com.hotel.ui.menucontroller.MenuController;

public class Run {
	public static void main(String[] args) {
		Hotel.getInstance().readFromFile();
		MenuController menu = new MenuController();
		menu.run();
		Hotel.getInstance().writeInFile();
	}

}
