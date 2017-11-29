package com.hotel.ui.actions.guest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class GetTotalPaymentAction implements IAction {
	
	final static Logger logger = Logger.getLogger(GetTotalPaymentAction.class);

	@Override
	public void execute() {
BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		
		try {
			Hotel hotel= Hotel.getInstance();
			Printer.println("Enter guest id");
			String idStr = reader.readLine();
			Integer id = Integer.valueOf(idStr);
			if(hotel.getGuestById(id).getHistory()!=null) {
			Printer.print("Total payment of guest "+hotel.getGuestById(id).getName()+" "+
			hotel.getTotalPayment(id)+"\n");
			}else {
				Printer.println("This guest is not settled in any of the rooms");
			}
		} catch (IOException e) {
			logger.error("Exception in the class GetTotalPayment"+e.getMessage());
			Printer.println("This guest is not settled in any of the rooms: "+e.getMessage());
		}
	}
}
