package com.hotel.ui.actions.option;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class AddOptionAction implements IAction {
	
	final static Logger logger = Logger.getLogger(AddOptionAction.class);

	@Override
	public void execute() {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the option name ");
			String name=reader.readLine();
			Printer.print("Enter the price ");
			String priceString = reader.readLine();
			Double price= Double.valueOf(priceString);
			Hotel.getInstance().addOption(name, price);

		} catch (ArrayIndexOutOfBoundsException|IOException e) {
			Printer.println("Incorrect input data: " + e.getMessage());
			logger.error("Exception in the class SettleGuestInRoom: " + e.getMessage());
		}
		
	}

}
