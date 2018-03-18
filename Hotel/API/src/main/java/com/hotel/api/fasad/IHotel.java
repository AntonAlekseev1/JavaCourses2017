package com.hotel.api.fasad;

import com.hotel.been.History;
import com.hotel.been.User;

public interface IHotel {

	public String exportGuests();

	public String importGuest();

	public String addGuest(String name, String lastName);

	public String getNumberOfGuests();

	public String getGuests();

	public String getGuestById(String idStr);

	public String getGuestOptions(String guestIdStr);

	public String addOptionToGuest(String optionIdStr, String guestIdStr);

	public String sortedGuests(String name);

	public String remuveGuest(String idStr);

	public String getTotalPayment(String idStr);

	public String exportRooms();

	public String importRooms();

	public String addRoom(String numberStr, String copacityStr, String numberOfStarsStr, String priceStr);

	public String getAllRooms();

	public String getRoomById(String idStr);

	public String removeRoom(String idStr);

	public String getFreeRooms();

	public String getNumberOfRooms();

	public String getNumberOfFreeRooms();

	public String sortRooms(String name);

	public String chengePriceOfRoom(String idStr, String priceStr);

	public String changeRoomStatus(String idStr, String n);

	public String getLastGuests(String idStr);

	public String clone(String idStr, String num);

	public String settleGuestInRoom(String guestIdStr, String roomIdStr, String arivalDayStr, String arivalMonthStr,
			String arivalYearStr, String evictDayStr, String evictMonthStr, String evictYearStr);

	public String getFreeRoomsOnDate(String dayStr, String manthStr, String yearStr);

	public void addHistory(History history);

	public String evictGuestFromRoom(String guestIdStr, String roomIdStr);

	public String getHistory();

	public String exportOptions();

	public String importOptions();

	public String getOptionById(String idStr);

	public String addOption(String name, String priceStr);

	public String getAllOptions();

	public String register(String login, String password);

	public User signIn(String login, String password);

}
