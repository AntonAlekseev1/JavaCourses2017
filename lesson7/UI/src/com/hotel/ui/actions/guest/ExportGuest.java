package com.hotel.ui.actions.guest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.hotel.annatation.CsvEntity;
import com.hotel.configurations.Configuration;
import com.hotel.fasad.Hotel;
import com.hotel.ui.action.IAction;
import com.hotel.utils.Printer;

public class ExportGuest implements IAction{
	
	private final static Logger logger = Logger.getLogger(ExportGuest.class);
	private static final String PATH_TO_GUESTS_CSV= String.valueOf(Configuration.getProperties("PATH_TO_GUESTS_CSV"));
	private String path;



	@Override
	public void execute() {
		
		String fileName=null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			Class<?> c = Class.forName("com.hotel.been.Guest");
			CsvEntity ann = c.getAnnotation(CsvEntity.class);
			 fileName=ann.filename();
		Printer.println("Enter file name ");
			path=PATH_TO_GUESTS_CSV+reader.readLine();
		} catch (IOException|ClassNotFoundException e) {
			logger.info("Exception in class ExportGuest "+e.getMessage());
		}
		File file = new File(path);
		if(file.exists()&&file.isFile()) {
			Hotel.getInstance().exportGuests(path);
			Printer.println("Export was successful");
		}else {
			Printer.println("File not found, export by default path");
			Hotel.getInstance().exportGuests(PATH_TO_GUESTS_CSV+fileName);
		}
		
		
	}

}
