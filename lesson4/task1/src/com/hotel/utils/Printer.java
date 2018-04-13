package com.hotel.utils;

<<<<<<< HEAD:lesson4/task1/src/com/hotel/utils/Printer.java
import com.hotel.been.Entity;
=======
import java.util.List;
>>>>>>> lesson11:Hotel/Util/src/com/hotel/utils/Printer.java

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

<<<<<<< HEAD:lesson4/task1/src/com/hotel/utils/Printer.java
	public static void printArray(Entity[] entity) {
		for (int i = 0; i < entity.length; i++) {
			if (entity[i] != null) {
				System.out.println(entity[i].toString());
			}
=======
	public static void printArray(List<?> entity) {
		for (int i = 0; i < entity.size(); i++) {

			System.out.println(entity.get(i).toString());
>>>>>>> lesson11:Hotel/Util/src/com/hotel/utils/Printer.java
		}
	}

	public static void printArray(String[] entity) {
		for (int i = 0; i < entity.length; i++) {
			if (entity[i] != null) {
				System.out.println(entity[i].toString());
			}
		}
	}

<<<<<<< HEAD:lesson4/task1/src/com/hotel/utils/Printer.java
=======
	public static void println(Object object) {
		System.out.println(object);

	}

>>>>>>> lesson11:Hotel/Util/src/com/hotel/utils/Printer.java
}
