package com.hotel.ui.navigator;

import com.hotel.ui.action.MenuItem;
import com.hotel.ui.menu.Menu;
import com.hotel.utils.Printer;

public class Navigator {

	private Menu currentMenu;

	public void printMenu() {
		try {
		for (int i = 0; i < currentMenu.getMenuItems().size(); i++) {
			if(!currentMenu.equals(null)) {
			Printer.println(i + ") " + currentMenu.getMenuItems().get(i).getTitle());
			}
		}
		}catch(NullPointerException e) {
			
		}
	}

	public void navigate(Integer index) {
		if(!currentMenu.equals(null)) {
		MenuItem menuItem = currentMenu.getMenuItems().get(index);
			menuItem.doAction();
			currentMenu = menuItem.getNextMenu();
			printMenu();
			}
	}

	public Menu getCurrentMenu() {
		return currentMenu;
	}

	public void setCurrentMenu(Menu currentMenu) {
		this.currentMenu = currentMenu;
	}
	
}
