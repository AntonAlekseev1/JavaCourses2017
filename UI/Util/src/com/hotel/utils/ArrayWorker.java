package com.hotel.utils;

import java.util.List;

public class ArrayWorker {
	public static final Integer MIN_SIZE = 5;

	public static Integer getCount(Object[] array) {
		int count = 0;
		for (Object obj : array) {
			if (obj != null) {
				count++;
			} else {
				break;
			}
		}
		return count;
	}

	public static int getCount(List<?> array) {
		int count = 0;
		for (Object obj : array) {
			if (obj != null) {
				count++;
			} 
		}
		return count;
	}
	
	public static String[] toString(List<?> list) {
		
		String[] array=new String[list.size()];
		for(int i=0;i<list.size();i++) {
			array[i]=list.get(i).toString();
		}
		return array;
	}
}
