package com.hotel.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.hotel.fasad.Wrapper;
import com.hotel.utils.Printer;

<<<<<<< HEAD
public class ConnectionListner extends Thread{
	
=======
public class ConnectionListner extends Thread {

>>>>>>> lesson11
	private final static Logger logger = Logger.getLogger(ConnectionListner.class);
	private final Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
<<<<<<< HEAD
	
	private Wrapper wrapper;
	
	public ConnectionListner(Socket socket, Wrapper wrapper) throws IOException{
		
=======

	private Wrapper wrapper;

	public ConnectionListner(Socket socket, Wrapper wrapper) throws IOException {

>>>>>>> lesson11
		this.wrapper = wrapper;
		this.socket = socket;
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
<<<<<<< HEAD
	
=======

>>>>>>> lesson11
	@Override
	public void run() {
		try {
			while (socket.isConnected()) {
				String request = reader.readLine();
				String response = null;
				if (request != null) {
					response = wrapper.getResponse(request);
<<<<<<< HEAD
					writer.write(response+"\r\n");
=======
					writer.write(response + "\r\n");
>>>>>>> lesson11
					writer.flush();
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
<<<<<<< HEAD
			Printer.println("I/O Exception "+e.getMessage());
		} finally {
			disconnect();
		}
		
	}
	
	public void disconnect() {
		
		try {
			socket.close();
			reader.close();
			writer.close();
		}catch(IOException e) {
			logger.info(e.getMessage());
		}finally {
			this.interrupt();
		}
	}
	
	
=======
			Printer.println("I/O Exception " + e.getMessage());
		} finally {
			disconnect();
		}

	}

	public void disconnect() {

		try {
			if (socket != null) {
				socket.close();
			}
			if (reader != null) {
				reader.close();
			}
			if (writer != null) {
				writer.close();
			}
		} catch (IOException e) {
			logger.info(e.getMessage());
		} finally {
			this.interrupt();
		}
	}
>>>>>>> lesson11

}
