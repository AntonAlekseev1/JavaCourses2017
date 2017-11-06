package com.hotel.utils;

import com.hotel.been.Entity;

public class Printer {
	public static void print(String message) {
		System.out.print(message);
	}

	public static void println(String message) {
		System.out.println(message);

	}

	public static void println(Double number) {
		System.out.println(number);

	}

	public static void printArray(Entity[] entity) {
		for (int i = 0; i < entity.length; i++) {
			if (entity[i] != null) {
				System.out.println(entity[i].toString());
			}
		}
	}

	public static void printArray(String[] entity) {
		for (int i = 0; i < entity.length; i++) {
			if (entity[i] != null) {
				System.out.println(entity[i].toString());
			}
		}
	}

}
