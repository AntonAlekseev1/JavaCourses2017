package com.hotel.ui.actions.guest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class TotalPayment implements IAction {

	@Override
	public void execute() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			Printer.println("Enter guest id");
			Integer id=reader.read();
			
			Printer.print("Total payment of guest"+Hotel.getInstance().getGuestById(id).getName()+" ");
			Printer.println(Hotel.getInstance().getTotalPayment(id));
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}

}
