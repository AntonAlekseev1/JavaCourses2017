package com.hotel.ui.actions.guest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.api.been.IGuest;
import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class GuestOptions implements IAction {
	
	final static Logger logger = Logger.getLogger(GuestOptions.class);

	@Override
	public void execute() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try  {
			Printer.println("Enter guest number");
			String guestIdStr=reader.readLine();
			Integer guestId = Integer.valueOf(guestIdStr);
			IGuest guest = Hotel.getInstance().getGuestById(guestId);
			if(guest.getHistory()!=null) {
				if(guest.getHistory().getOptions()!=null) {
			Printer.println("Options of guest " + guest.getName());
			Printer.printArray(Hotel.getInstance().getGuestOptions(guestId));
				}
			}else {
				Printer.println("This guest does not have any options");
			}
		} catch (IOException e) {
			logger.error("Exception in the class GuestOptions: " + e.getMessage());
		}
	}
}
