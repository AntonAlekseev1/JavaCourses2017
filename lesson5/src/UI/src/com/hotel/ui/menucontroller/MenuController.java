package com.hotel.ui.menucontroller;

import java.util.Scanner;

import com.hotel.ui.builder.Builder;
import com.hotel.ui.navigator.Navigator;

public class MenuController {

	private Builder builder;
	private Navigator navigator;

	public MenuController() {
		builder = new Builder();
		builder.buildMenu();
		navigator = new Navigator();
	}

	public void run() {

		String string;
		Scanner sc = new Scanner(System.in);
		navigator.setCurrentMenu(builder.getRootMenu());
		navigator.printMenu();
		do {
			string = sc.nextLine();
			Integer num = Integer.valueOf(string);
			navigator.navigate(num);
		} while (!string.equals("0"));

		sc.close();
		System.out.println("program closed");
	}

}
