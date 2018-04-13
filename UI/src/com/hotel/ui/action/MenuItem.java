package com.hotel.ui.action;

import com.hotel.ui.menu.Menu;

public class MenuItem {

	private String title;
	private IAction action;
	private Menu nextMenu;

	public MenuItem(String title, Menu nextMenu, IAction action) {
		this.title = title;
		this.nextMenu = nextMenu;
		this.action = action;

	}

	public void doAction() {
<<<<<<< HEAD:UI/src/com/hotel/ui/action/MenuItem.java
	 action.execute();
=======
		action.execute();
>>>>>>> lesson11:UI/src/com/hotel/ui/action/MenuItem.java
	}

	public Menu getNextMenu() {
		return nextMenu;
	}

	public String getTitle() {
		return title;
	}

	public IAction getAction() {
		return action;
	}

	@Override
	public String toString() {
		return title;
	}

}
