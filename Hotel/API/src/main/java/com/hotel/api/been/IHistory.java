package com.hotel.api.been;

import java.util.Date;
import java.util.List;

public interface IHistory extends IEntity {

	Date getDateOfArrival();

	IGuest getGuest();

	void setGuest(IGuest guest);

	IRoom getRoom();

	void setRoom(IRoom room);

	Date getEvictDate();

	List<IOption> getOptions();

	void setOptions(List<IOption> options);

	void setDateOfArival(Date dateOfArival);

	void setEvictDate(Date evictDate);
}