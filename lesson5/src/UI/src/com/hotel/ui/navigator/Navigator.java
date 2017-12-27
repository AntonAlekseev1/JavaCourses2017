package com.hotel.ui.navigator;

import com.hotel.ui.action.MenuItem;
import com.hotel.ui.menu.Menu;

public class Navigator {

	private Menu currentMenu;

	public void printMenu() {
		for (int i = 0; i < currentMenu.getMenuItems().size(); i++) {
			System.out.println(i + 1 + ") " + currentMenu.getMenuItems().get(i).getTitle());
		}

	}

	public void navigate(Integer index) {
		MenuItem menuItem = currentMenu.getMenuItems().get(index);
		if (menuItem.getAction() != null) {
			menuItem.doAction();
		} else {
			currentMenu = menuItem.getNextMenu();
		}
	}

	public Menu getCurrentMenu() {
		return currentMenu;
	}

	public void setCurrentMenu(Menu currentMenu) {
		this.currentMenu = currentMenu;
	}

}
