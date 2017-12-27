package com.hotel.comparator;

import java.util.Comparator;

import com.hotel.been.Guest;

public class SortedByName implements Comparator<Guest> {

	@Override
	public int compare(Guest o1, Guest o2) {
		if (o1 != null && o2 != null) {

			return o1.getName().compareTo(o2.getName());
		}
		return 0;
	}

}