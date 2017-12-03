package com.hotel.ui.actions.history;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class SettleGuestInRoom implements IAction{
	
	final static Logger logger = Logger.getLogger(SettleGuestInRoom.class);

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
			if(Hotel.getInstance().getGuestById(guestId)!=null &&Hotel.getInstance().getRoonById(roomId)!=null) {
			Hotel.getInstance().settleGuestInRoom(guestId, roomId, new GregorianCalendar(arivalYear, arivalMonth, arivalDay),
					                                                  new GregorianCalendar(evictYear, evictMonth, evictDay));
			}else {
				Printer.println("Incorrect input data");
			}

		} catch (ArrayIndexOutOfBoundsException|IOException e) {
			Printer.println("Incorrect input data: " + e.getMessage());
			logger.error("Exception in the class SettleGuestInRoom: " + e.getMessage());
		}
		
	}

}
