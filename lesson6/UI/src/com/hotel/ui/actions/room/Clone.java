package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hotel.api.been.IRoom;
import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class Clone implements IAction{

	@Override
	public void execute()  {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the original room id ");
			String idString=reader.readLine();
			Integer id=Integer.valueOf(idString);
			IRoom clonRoom = Hotel.getInstance().clone(id);
			Printer.print("Do you want to modify room clon? Y/N ");
			String answer=reader.readLine();
			if(answer.equals("Y")|answer.equals("y")) {
				Printer.print("Enter the room copacity ");
				String copacityString=reader.readLine();
				Integer copacity=Integer.valueOf(copacityString);
				clonRoom.setCopacity(copacity);
				Printer.print("Enter the room number of stars ");
				String starsStr=reader.readLine();
				Integer stars=Integer.valueOf(starsStr);
				clonRoom.setNumberOfStars(stars);
				Printer.print("Enter the room price ");
				String priceStr=reader.readLine();
				Double price=Double.valueOf(priceStr);
				clonRoom.setPrice(price);
			}
			Hotel.getInstance().getAllRooms().add(clonRoom);
		}catch(IOException | CloneNotSupportedException e) {
			
		}
		
	}
	

}
