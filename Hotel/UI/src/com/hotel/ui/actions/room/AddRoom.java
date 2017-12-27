package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class AddRoom implements IAction {

	@Override
	public void execute() {
		try(BufferedReader reader=new BufferedReader(new InputStreamReader(System.in))) {
			Printer.print("Enter the copaciti ");
			Integer copacity=reader.read();
			Printer.print(" Enter the number of stars ");
			Integer numberOfStars=reader.read();
			Printer.print(" Enter the price ");
			Double price=(double) reader.read();
			Hotel.getInstance().addRoom(copacity, numberOfStars, price);

		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}

}
