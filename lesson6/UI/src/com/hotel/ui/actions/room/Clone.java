package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class Clone implements IAction{
	
	private final static Logger logger = Logger.getLogger(Clone.class);

	@Override
	public void execute()  {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		try {
			Printer.print("Enter the original room id ");
			String idString=reader.readLine();
			Integer id=Integer.valueOf(idString);
			Printer.print("Do you want to modify room clon? Y/N ");
			String answer=reader.readLine();
			Integer copacity=null;
			Integer stars=null;
			Double price=null;
			if(answer.equals("Y")|answer.equals("y")) {
				Printer.println("Enter the room copacity ");
				String copacityStr=reader.readLine();
			    copacity=Integer.valueOf(copacityStr);
				Printer.println("Enter the room number of stars ");
				String starsStr=reader.readLine();
			    stars=Integer.valueOf(starsStr);
				Printer.println("Enter the room price ");
				String priceStr=reader.readLine();
				price=Double.valueOf(priceStr);
			}
			Hotel.getInstance().clone(id, answer, copacity, stars, price);
		}catch(IOException | CloneNotSupportedException|NumberFormatException e) {
			Printer.println("Incorrect input data: "+e.toString());
			logger.error("Exception: "+e.getMessage());
		}
		
	}
	

}
