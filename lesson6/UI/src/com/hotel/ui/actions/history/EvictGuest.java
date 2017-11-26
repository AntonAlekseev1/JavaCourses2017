package com.hotel.ui.actions.history;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class EvictGuest implements IAction{

	@Override
	public void execute() {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the guest id ");
			String guestIdStr=reader.readLine();
			Integer guestId= Integer.valueOf(guestIdStr);
			Printer.print("Enter the room id ");
			String roomIdStr=reader.readLine();
			Integer roomId=Integer.valueOf(roomIdStr);
			
			Hotel.getInstance().evictGuestFromRoom(guestId, roomId);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
