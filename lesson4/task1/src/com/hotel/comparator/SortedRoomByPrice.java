package com.hotel.comparator;

import java.util.Comparator;

import com.hotel.been.Room;

public class SortedRoomByPrice implements Comparator<Room> {

	@Override
	public int compare(Room o1, Room o2) {
		if (o1 != null && o2 != null) {
			Double price1 = o1.getPrice();
			Double price2 = o2.getPrice();

			if (price1 > price2) {
				return 1;
			} else if (price1 < price2) {
				return -1;
			} else {
				return 0;

			}
		}
		return 0;
	}

}
