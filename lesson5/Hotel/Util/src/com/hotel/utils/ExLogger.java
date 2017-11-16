package com.hotel.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class ExLogger {
	
	 private static Logger log = Logger.getLogger(ExLogger.class.getName());
	 
	 public static void write(Exception e) {
		 FileHandler fh;
		try {
			fh = new FileHandler("D:\\1\\Logg.log",true);
			log.addHandler(fh);
		} catch (SecurityException ex) {
			e.printStackTrace();
		} catch (IOException ex) {
			e.printStackTrace();
		}
	        log.info(e.toString());
	    }    
}
