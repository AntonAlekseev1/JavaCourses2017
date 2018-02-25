package com.hotel.ui.menu;

import java.util.ArrayList;
import java.util.List;

import com.hotel.ui.action.MenuItem;

public class Menu {

	private String name;
	private final List<MenuItem> menuItems = new ArrayList<>();

	public Menu(String name) {
		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void addItem(MenuItem item) {
		menuItems.add(item);
	}

}
