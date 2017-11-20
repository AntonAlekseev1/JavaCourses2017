package com.hotel.ui.actions.guest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hotel.api.been.IGuest;
import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class GuestOptions implements IAction {

	@Override
	public void execute() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try  {
			Printer.println("Enter guest number");
			String guestIdStr=reader.readLine();
			Integer guestId = Integer.valueOf(guestIdStr);
			IGuest guest = Hotel.getInstance().getGuestById(guestId);

			Printer.println("Options of guest " + guest.getName());
			Printer.printArray(Hotel.getInstance().getGuestOptions(guestId));

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
