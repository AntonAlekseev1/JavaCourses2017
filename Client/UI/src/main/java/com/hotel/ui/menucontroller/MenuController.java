package com.hotel.ui.menucontroller;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.hotel.ui.builder.Builder;
import com.hotel.ui.navigator.Navigator;
import com.ui.util.Printer;

public class MenuController {

	private Builder builder;
	private Navigator navigator;

	final static Logger logger = Logger.getLogger(MenuController.class);

	public MenuController() {

		builder = new Builder();
		builder.buildMenu();
		navigator = new Navigator();
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		try {
			String string = "";

			navigator.setCurrentMenu(builder.getRootMenu());
			navigator.printMenu();

			while (!string.equals("0")) {
				string = sc.nextLine();
				Integer num = Integer.valueOf(string);
				navigator.navigate(num);
			}
		} catch (Exception e) {
			Printer.println("Exception in class MenuController/in method run: " + e.getMessage());
			logger.info("mesege", e);
		} finally {
			sc.close();
			Printer.println("program closed");
		}

	}

}
