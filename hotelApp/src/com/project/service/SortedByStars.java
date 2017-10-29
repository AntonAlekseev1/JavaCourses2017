package com.project.service;

import java.util.Comparator;

import com.project.been.Room;

public class SortedByStars implements Comparator<Room> {

	@Override
	public int compare(Room o1, Room o2) {
		if (o1 != null && o2 != null) {
			Integer numberOfStars1 = o1.getNumberOfStars();
			Integer numberOfStars2 = o2.getNumberOfStars();

			if (numberOfStars1 > numberOfStars2) {
				return 1;
			} else if (numberOfStars1 < numberOfStars2) {
				return -1;
			} else {
				return 0;
			}
		}
		return 0;
	}

}
