package com.hotel.ui.action.option;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class OptionById implements IAction {

	@Override
	public void execute() {
		try(BufferedReader reader=new BufferedReader(new InputStreamReader(System.in))) {
			Printer.print("Enter the option id ");
			Integer id=reader.read();
			Printer.println(Hotel.getInstance().getOptionById(id));
			
		}catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}


}
