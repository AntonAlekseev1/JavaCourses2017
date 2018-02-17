package com.hotel.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.hotel.fasad.Wrapper;
import com.hotel.utils.Printer;

public class Server {
	
	private final static  Logger logger = Logger.getLogger(Server.class);
	private final ArrayList<ConnectionListner> connections = new ArrayList<>();
	
	public Server() {
		try(ServerSocket serverSocket = new ServerSocket(8030)){
			while(true) {
				Socket socket = serverSocket.accept();
				Wrapper wrapper = new Wrapper();
				ConnectionListner listner = new ConnectionListner(socket, wrapper);
				connections.add(listner);
				listner.start();
			}
		}catch(IOException e) {
			logger.error(e.getMessage());
			Printer.println("Server error "+e.getMessage());
		}
	}

}
