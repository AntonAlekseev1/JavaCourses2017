package com.hotel.utils;

import java.util.List;


public class Printer {
	public static void print(String message) {
		System.out.print(message);
	}

	public static void println(String message) {
		System.out.println(message);

	}

	public static void println(Integer number) {
		System.out.println(number);

	}
	
	public static void printArray(List<?> entity) {
		for (int i = 0; i < entity.size(); i++) {
			
				System.out.println(entity.get(i).toString());
			}
		}
	

	public static void printArray(String[] entity) {
		for (int i = 0; i < entity.length; i++) {
			if (entity[i] != null) {
				System.out.println(entity[i].toString());
			}
		}
	}

	public static void println( Object object) {
		System.out.println(object);
		
	}

}
