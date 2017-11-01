package com.hotel.comparator;

import java.util.Comparator;

import com.hotel.been.Guest;

public class SortedByName implements Comparator<Guest> {

	@Override
	public int compare(Guest o1, Guest o2) {
		if (o1 != null && o2 != null) {
			String guest1 = o1.getName();
			String guest2 = o2.getName();

			return guest1.compareTo(guest2);
		}
		return 0;
	}

}