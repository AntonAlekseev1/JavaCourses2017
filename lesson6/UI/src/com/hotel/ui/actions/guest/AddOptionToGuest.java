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
BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		
		try {
			Printer.println("Enter option id");
			String idStr=reader.readLine();
			Integer optionId=Integer.valueOf(idStr);
			
			Printer.println("Enter guest id");
			String guestIdStr=reader.readLine();
			Integer guestId=Integer.valueOf(guestIdStr);
			
			Hotel.getInstance().addOptionToGuest(optionId, guestId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
