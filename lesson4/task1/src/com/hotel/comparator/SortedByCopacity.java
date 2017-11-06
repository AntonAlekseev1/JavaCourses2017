package com.hotel.comparator;

import java.util.Comparator;

import com.hotel.been.Room;

public class SortedByCopacity implements Comparator<Room> {
	@Override
	public int compare(Room o1, Room o2) {
		if (o1 != null && o2 != null) {

			return o1.getCopacity().compareTo(o2.getCopacity());
		}
		return 0;
	}

}
