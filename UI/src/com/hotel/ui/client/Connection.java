package com.hotel.ui.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.hotel.ui.api.IConnection;
import com.hotel.utils.Printer;

public class Connection implements IConnection{
	
	private static final Logger logger = Logger.getLogger(Connection.class);
	
	private BufferedReader reader;
	private BufferedWriter writer;
	private Socket socket;
	private static Connection instance;
	

	private  Connection() {
		
		try {
			int port = 8030;
		if(socket==null) {	
			socket = new Socket(InetAddress.getLocalHost(), port);
		}	
		if(writer==null) {
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		}
		if(reader==null) {
		
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
			
		} catch (UnknownHostException e) {
			logger.error(e.getMessage());
			Printer.println("UnknownHostException: "+e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
			Printer.println("Connection Exception: "+e.getMessage());
		}
		
	}
	
	public static Connection getInstance() {
		if(instance==null) {
			instance = new Connection();
		}
		return instance;
	}
	
	@Override
	public String getResponseFromServer(String value) {
		
		String response=null;
		try {
			writer.write(value);
			writer.newLine();
			Printer.println(value);
			writer.flush();
		    response = reader.readLine().replaceAll(",", "\n");
		}catch(IOException e) {
			logger.error(e.getMessage());
			Printer.println("Exception of I/O stream : "+e.getMessage());
		}
		
		return response;
	}
	
	@Override
	public BufferedWriter getWriter() throws IOException {
		if (writer == null) {
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		}
		return writer;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		if (reader == null) {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		return reader;
	}
	
	@Override
	public void close() {
		try {
			reader.close();
			writer.close();
			socket.close();
		}catch(IOException e) {
			logger.error(e.getMessage());
			Printer.println("Exception of I/O stream : "+e.getMessage());
		}
	}

}
