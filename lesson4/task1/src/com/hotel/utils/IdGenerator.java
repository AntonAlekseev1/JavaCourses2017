package com.hotel.utils;

//import com.danco.training.TextFileWorker;

public class IdGenerator {
	private static Integer roomId=0;
	private static Integer guestId=0;
	private static Integer optionId=0;
	
/*	public void start() {
		TextFileWorker text = new TextFileWorker("D:\\1\\idGenerator.txt");
		String[] number=text.readFromFile();
		roomId=Integer.valueOf(number[0]);
		guestId=Integer.valueOf(number[1]);
		optionId=Integer.valueOf(number[3]);
	}*/
	public static Integer generateRoomId() {
		roomId++;
		return roomId;
	}
	public static Integer generateGuestId() {
		guestId++;
		return guestId;
	}
	public static Integer generateOptionId() {
		optionId++;
		return optionId;
	}
/*	public void write() {
		TextFileWorker text = new TextFileWorker("D:\\1\\idGenerator.txt");
		text.writeToFile(new String[] {String.valueOf(roomId),String.valueOf(guestId),String.valueOf(optionId)});
	}*/

}
