package com.hotel.ui.actions.history;

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
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the date dd mm yyyy");
			String dateStr=reader.readLine();
			String[] arr = dateStr.split(" ");
			Integer day=Integer.valueOf(arr[0]);
			Integer manth=Integer.valueOf(arr[1]);
			Integer year=Integer.valueOf(arr[2]);
			Printer.printArray(Hotel.getInstance().getFreeRoomsOnDate( new GregorianCalendar(year,manth,day)));
		}catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}

}
