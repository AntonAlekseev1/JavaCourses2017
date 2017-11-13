package com.hotel.comparator;

import java.util.Comparator;

import com.hotel.been.Room;

public class SortedByStars implements Comparator<Room> {

	@Override
	public int compare(Room o1, Room o2) {
		if (o1 != null && o2 != null) {

			return o1.getNumberOfStars().compareTo(o2.getNumberOfStars());
		}
		return 0;
	}

}
