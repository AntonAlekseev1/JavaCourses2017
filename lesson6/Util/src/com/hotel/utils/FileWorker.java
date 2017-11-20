package com.hotel.utils;

import com.danco.training.TextFileWorker;

public class FileWorker {
	private static TextFileWorker fileWorker;

	public static void writeToFile(String path, String[] data) {
		fileWorker = new TextFileWorker(path);
		fileWorker.writeToFile(data);
	}

	public static String[] readFrom(String path) {
		fileWorker = new TextFileWorker(path);
		return fileWorker.readFromFile();
	}

}
