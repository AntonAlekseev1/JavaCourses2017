package com.hotel.ui.actions.option;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class AddOption implements IAction {

	@Override
	public void execute() {
		try(BufferedReader reader=new BufferedReader(new InputStreamReader(System.in))) {
			Printer.print("Enter the option name ");
			String name=reader.readLine();
			Printer.print(" Enter the price ");
			Double price=(double) reader.read();
			Hotel.getInstance().addOption(name, price);

		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}

}
