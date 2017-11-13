package com.hotel.utils;

//import com.danco.training.TextFileWorker;

public class IdGenerator {
	private static Integer roomId = 0;
	private static Integer guestId = 0;
	private static Integer optionId = 0;
	private static Integer historyId = 0;

	public static Integer generateHistoryId() {
		historyId++;
		return historyId;
	}

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

}
