package com.hotel.api.been;

import java.util.Date;
import java.util.List;

public interface IHistory extends IEntity {

	Date getDateOfArrival();

	Integer getGuestId();

	void setGuest(Integer guestId);

	Integer getRoomId();

	void setRoom(Integer roomId);

	Date getEvictDate();

	List<IOption> getOptions();

	void setOptions(List<IOption> options);

	void setDateOfArival(Date dateOfArival);

	void setEvictDate(Date evictDate);
}
