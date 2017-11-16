package com.hotel.ui.actions.history;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class SettleGuestInRoom implements IAction{

	@Override
	public void execute() {
		try(BufferedReader reader=new BufferedReader(new InputStreamReader(System.in))) {
			Printer.print("Enter the guest id ");
			Integer guestId=reader.read();
			Printer.print("Enter the room id ");
			Integer roomId=reader.read();
			Printer.print("Enter the date of arival ");
			String dateOfArival=reader.readLine();
			String[] arr = dateOfArival.split(" ");
			Integer arivalDay=Integer.valueOf(arr[0]);
			Integer arivalMonth=Integer.valueOf(arr[1]);
			Integer arivalYear=Integer.valueOf(arr[2]);
			Printer.print("Enter the date of evict ");
			String dateOfEvict=reader.readLine();
			String[] array = dateOfEvict.split(" ");
			Integer evictDay=Integer.valueOf(array[0]);
			Integer evictMonth=Integer.valueOf(array[1]);
			Integer evictYear=Integer.valueOf(array[2]);
			Hotel.getInstance().settleGuestInRoom(guestId, roomId, new GregorianCalendar(arivalYear, arivalMonth, arivalDay),
					                                                  new GregorianCalendar(evictYear, evictMonth, evictDay));

		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}

}
