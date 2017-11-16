package com.hotel.ui.actions.guest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class AddOptionToGuest implements IAction{

	@Override
	public void execute() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			Printer.println("Enter option id");
			Integer optionId=reader.read();
			
			Printer.println("Enter guest id");
			Integer guestId=reader.read();
			
			Hotel.getInstance().addOptionToGuest(optionId, guestId);
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	

}
