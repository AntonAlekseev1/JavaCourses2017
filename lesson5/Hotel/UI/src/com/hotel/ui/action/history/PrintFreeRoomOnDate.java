package com.hotel.ui.action.history;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class PrintFreeRoomOnDate implements IAction{

	@Override
	public void execute() {
		try(BufferedReader reader=new BufferedReader(new InputStreamReader(System.in))) {
			Printer.print("Enter the date dd--mm--yyyy");
			Integer day=reader.read();
			Integer manth=reader.read();
			Integer year=reader.read();
			Hotel.getInstance().getFreeRoomsOnDate( new GregorianCalendar(year,manth,day));
		}catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}

}
