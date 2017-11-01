package com.hotel.comparator;

import java.util.Comparator;

import com.hotel.been.Room;

public class SortedByCopacity implements Comparator<Room> {
	@Override
	public int compare(Room o1, Room o2) {
		if (o1 != null && o2 != null) {
			Integer copacity1 = o1.getCopacity();
			Integer copacity2 = o2.getCopacity();

			if (copacity1 > copacity2) {
				return 1;
			} else if (copacity1 < copacity2) {
				return -1;
			} else {
				return 0;
			}
		}
		return 0;
	}

}
