package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class LastGuests implements IAction {

	@Override
	public void execute() {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the room id ");
			Integer id=reader.read();
			Printer.println("last guests of the room "+Hotel.getInstance().getRoonById(id).getId());
			Hotel.getInstance().getLastGuests(id);
			
		}catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}

}
