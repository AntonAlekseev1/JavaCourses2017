package com.project.been;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class History {
	private int roomId;
	private int guestId;
	private Date dateOfArrival;
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public int getGuestId() {
		return guestId;
	}
	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}
	public Date getDateOfArrival() {
	
		return dateOfArrival;
	}
	public void setDateOfArrival(String date) throws ParseException {
		
		Date dateOfArrival = new SimpleDateFormat( "dd.MM.yyyy", Locale.ENGLISH ).parse( date );
		this.dateOfArrival = dateOfArrival;
	}

}
