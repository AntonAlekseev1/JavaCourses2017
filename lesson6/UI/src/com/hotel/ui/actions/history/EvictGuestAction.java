package com.hotel.ui.actions.history;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class EvictGuestAction implements IAction{
	
	final static Logger logger = Logger.getLogger(EvictGuestAction.class);

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
			if(Hotel.getInstance().getGuestById(guestId)!=null) {
			Hotel.getInstance().evictGuestFromRoom(guestId, roomId);
			}else {
				Printer.println("Incorrect guest id");
			}
		} catch (IOException e) {
			Printer.println("Exception in the class EvictGuestAction: " + e.getMessage());
			logger.error("Exception in the class EvictGuestAction: " + e.getMessage());
		}
		
		
	}

}
