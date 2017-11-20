package com.hotel.ui.menucontroller;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hotel.ui.builder.Builder;
import com.hotel.ui.navigator.Navigator;
import com.hotel.utils.Printer;

public class MenuController {

	private Builder builder;
	private Navigator navigator;
	private static Logger logger = Logger.getLogger("Logger");

	public MenuController() {
		Handler fh;
		try {
             fh = new FileHandler("../data/logger.log",true);
           logger.addHandler(fh);
       } catch ( IOException e) {
           Printer.print("No logger file");
       }
		builder = new Builder();
		builder.buildMenu();
		navigator = new Navigator();
	}
	
	public void run()  {
		Scanner sc = new Scanner(System.in);
		try {
		String string="";
		
		navigator.setCurrentMenu(builder.getRootMenu());
		navigator.printMenu();

				while (!string.equals("0")) {
				string = sc.nextLine();
				Integer num = Integer.valueOf(string);
				navigator.navigate(num);
			 
		}
		}catch(Exception e) {
			Printer.println("Exception in class MenuController/in method run: "+e.getMessage());
			logger.log(Level.SEVERE, "Exception in class MenuController/in method run: ", e.getMessage());
		}
		sc.close();
		Printer.println("program closed");
		
	}

}
