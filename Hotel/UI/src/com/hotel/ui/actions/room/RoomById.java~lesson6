package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class RoomById implements IAction {

	@Override
	public void execute() {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the room id ");
			String idStr=reader.readLine();
			Integer id=Integer.valueOf(idStr);
			Printer.println(Hotel.getInstance().getRoonById(id));
			
		}catch (IOException e) {
			Printer.println("Exception in the class RoomById: "+e.getMessage());
		}
		
	}

}
