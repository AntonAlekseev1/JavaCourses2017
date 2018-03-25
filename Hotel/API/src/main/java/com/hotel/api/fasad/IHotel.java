package com.hotel.api.fasad;

import java.util.List;

import com.hotel.been.History;

public interface IHotel {

	public String exportGuests();

	public String importGuest();

	public String addGuest(String name, String lastName);

	public String getNumberOfGuests();

	public String getGuests();

	public String getGuestById(String idStr);

	public String getGuestOptions(String guestIdStr);

	public String addOptionToGuest(Integer optionI, Integer guestId);

	public String sortedGuests(String name);

	public String remuveGuest(Integer id);

	public Double getTotalPayment(Integer id) throws Exception;

	public String exportRooms();

	public String importRooms();

	public String addRoom(Integer number, Integer copacity, Integer stars, Double price);

	public String getAllRooms();

	public String getRoomById(String idStr);

	public String removeRoom(Integer id);

	public String getFreeRooms();

	public String getNumberOfRooms();

	public String getNumberOfFreeRooms();

	public String sortRooms(String name);

	public String chengePriceOfRoom(Integer id, Double price);

	public String changeRoomStatus(String idStr, String n);

	public String getLastGuests(String idStr);

	public String clone(String idStr, String num);

	public String settleGuestInRoom(Integer guestId, Integer roomId, String arivalDayStr, String arivalMonthStr,
			String arivalYearStr, String evictDayStr, String evictMonthStr, String evictYearStr);

	public String getFreeRoomsOnDate(String dayStr, String manthStr, String yearStr);

	public void addHistory(History history);

	public String evictGuestFromRoom(String guestIdStr, String roomIdStr);

	public List<History> getHistory() throws Exception;

	public String exportOptions();

	public String importOptions();

	public String getOptionById(String idStr);

	public String addOption(String name, Double price);

	public String getAllOptions();

	public String register(String login, String password);

	public String signIn(String login, String password);

	public String writeLog(String login, String action);

}
