package com.hotel.ui.actions.option;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.configurations.Configuration;
import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class ExportOptions implements IAction{
	
	private static final Logger logger = Logger.getLogger(ExportOptions.class);
	private static final String PATH_TO_OPTIONS_CSV = String.valueOf(Configuration.getProperties("PATH_TO_OPTIONS_CSV"));
	private String path;

	public ExportOptions() {
		Configuration.loadConfiguration();
	}

	@Override
	public void execute() {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
		Printer.println("Enter the path to csv file in the format folder/filename.csv ");
			path="../"+reader.readLine();
		} catch (IOException e) {
			logger.info("Exception in class ExportOptions "+e.getMessage());
		}
		File file = new File(path);
		if(file.exists()&&file.isFile()) {
			Hotel.getInstance().exportOptions(path);
			Printer.println("Export was successful");
		}else {
			Printer.println("File not found, export by default path");
			Hotel.getInstance().exportOptions(PATH_TO_OPTIONS_CSV);
		}
	
		
	}

}
