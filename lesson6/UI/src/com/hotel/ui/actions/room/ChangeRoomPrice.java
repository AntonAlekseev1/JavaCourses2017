package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class ChangeRoomPrice implements IAction {

	@Override
	public void execute() {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the room id ");
			String idStr=reader.readLine();
			Integer id = Integer.valueOf(idStr);
			Printer.print("Enter the new price ");
			String priceStr = reader.readLine();
			Double price=Double.valueOf(priceStr);
			Hotel.getInstance().chengePriceOfRoom(id, price);
			
		}catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}

}
