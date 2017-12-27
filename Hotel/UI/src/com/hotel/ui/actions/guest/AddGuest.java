package com.hotel.ui.actions.guest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class AddGuest implements IAction {

	@Override
	public void execute() {
		
		try(BufferedReader reader=new BufferedReader(new InputStreamReader(System.in))) {
			Printer.print("Enter the name ");
			String name=reader.readLine();
			Printer.print(" Enter the last name ");
			String lastName=reader.readLine();
			Hotel.getInstance().addGuest(name, lastName);

		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}
	
	

}
