package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class AddRoom implements IAction {
	
	private final static Logger logger = Logger.getLogger(AddRoom.class);

	@Override
	public void execute() {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the copaciti ");
			String cop=reader.readLine();
			Integer copacity=Integer.valueOf(cop);
			Printer.print("Enter the number of stars ");
			String stars=reader.readLine();
			Integer numberOfStars=Integer.valueOf(stars);
			Printer.print("Enter the price ");
			String priceString=reader.readLine();
			Double price=Double.valueOf(priceString);
			Hotel.getInstance().addRoom(copacity, numberOfStars, price);

		} catch (NumberFormatException|IOException e) {
			Printer.println("Incorrect input data: " + e.getMessage());
			logger.error("Exception in the class AddRoom: " + e.getMessage());
		}
		
	}

}
