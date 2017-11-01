package com.hotel.utils;

import com.hotel.been.Entity;

public class ArrayWorker {
	public static final Integer MIN_SIZE = 5;

	public static Entity[] resize(Entity[] entity) {
		Integer newSize = entity.length * 2;
		Entity[] ent = new Entity[newSize];
		System.arraycopy(entity, 0, ent, 0, entity.length);
		return ent;
	}

	public static Boolean isFreePlace(Entity[] entity) {
		return entity[entity.length - 1] == null;
	}

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
}
