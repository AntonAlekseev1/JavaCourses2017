package com.hotel.ui.actions.room;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.configurations.Configuration;
import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;


public class ImportRoom implements IAction {
	
	private final static Logger logger = Logger.getLogger(ImportRoom.class);
	private static final String PATH_TO_ROOMS_CSV= String.valueOf(Configuration.getProperties("PATH_TO_ROOMS_CSV"));
	private String path;
	
	@Override
	public void execute() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
		Printer.println("Enter the path to csv file in the format folder/filename.csv ");
			path="../"+reader.readLine();
		} catch (IOException e) {
			logger.info("Exception in class ImportRoom "+e.getMessage());
		}
		File file = new File(path);
		if(file.exists()&&file.isFile()) {
			Hotel.getInstance().importRooms(path);
			Printer.println("Import was successful");
		}else {
			Printer.println("File not found, import from default path");
			Hotel.getInstance().importRooms(PATH_TO_ROOMS_CSV);
		}
	}

}
