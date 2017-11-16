package com.hotel.ui.action.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class RoomById implements IAction {

	@Override
	public void execute() {
		try(BufferedReader reader=new BufferedReader(new InputStreamReader(System.in))) {
			Printer.print("Enter the room id ");
			Integer id=reader.read();
			Printer.println(Hotel.getInstance().getRoonById(id));
			
		}catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}

}
