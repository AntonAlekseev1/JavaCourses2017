package com.hotel.fasad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.hotel.api.been.IGuest;
import com.hotel.utils.Printer;


public class Wrapper {
	private final static Logger logger = Logger.getLogger(Wrapper.class);
	private String response = "response";
	
	public  String getResponse(String request) {
		Integer id = null;
		Integer roomId=null;
		Double price = null;
		Integer copacity = null;
		List<?> entity = new ArrayList<>();
		String []arrey = request.split(" ");
		String actionName = String.valueOf(arrey[0]);
		switch(actionName) {
	//Guest block
		case "SaveData":
			
			try {
				Hotel.getInstance().exit();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			response = "data saved";
			break;
		
		case "AddGuest":
			String name = String.valueOf(arrey[1]);
			String lastName = String.valueOf(arrey[2]);
			Hotel.getInstance().addGuest(name, lastName);
			response = "Guest "+name+" "+lastName+" was added";
			break;
			
		case "NumberOfGuests":
			response = Hotel.getInstance().getNumberOfGuests().toString();
			break;
			
		case "ShowAllGuests":
			StringBuilder builder = new StringBuilder();
			entity = Hotel.getInstance().getGuests();
			for (int i = 0; i < entity.size(); i++) {
				builder.append(entity.get(i).toString());
				builder.append(";");
				}
			response = builder.toString();
			break;
			
		case "GuestOptions":
			id = Integer.valueOf(arrey[1]);
			IGuest guest = Hotel.getInstance().getGuestById(id);
			if(guest.getHistory()!=null) {
				if(guest.getHistory().getOptions()!=null) {
					StringBuilder guestOptoins = new StringBuilder();
					entity = Hotel.getInstance().getGuestOptions(id);
					for (int i = 0; i < entity.size(); i++) {
						guestOptoins.append(entity.get(i).toString());
						guestOptoins.append(";");
						}
					response = "Options of guest " + guest.getName()+";"+guestOptoins.toString();
				}
			}else {
				response = "This guest does not have any options";
			}
			break;
			
		case "AddOptionToGuest":
			Integer optionId=Integer.valueOf(arrey[1]);
			Integer guestId=Integer.valueOf(arrey[2]);
			Hotel.getInstance().addOptionToGuest(optionId, guestId);
			response = "Option #"+optionId+" was added to guest #"+guestId;
			break;
			
		case "SortGuestsByName":
			StringBuilder sortByName = new StringBuilder();
			 entity = Hotel.getInstance().sortedGuestByName();
			for (int i = 0; i < entity.size(); i++) {
				sortByName.append(entity.get(i).toString());
				sortByName.append(";");
				}
			response = sortByName.toString();
			break;
			
		case "GetTotalPayment":
			try {
			Hotel hotel= Hotel.getInstance();
			id = Integer.valueOf(arrey[1]);
			if(hotel.getGuestById(id).getHistory()!=null) {
				response = "Total payment of guest "+hotel.getGuestById(id).getName()+" "+
				hotel.getTotalPayment(id);
				}else {
					response = "This guest is not settled in any of the rooms";
				}
			} catch (Exception e) {
				logger.error("Exception in the class GetTotalPayment"+e.getMessage());
				Printer.println("This guest is not settled in any of the rooms: "+e.getMessage());
			}
			break;
			
		case "ExportGuest":
			Hotel.getInstance().exportGuests();
			response = "export was finished";
			break;
			
		case "ImportGuests":
			Hotel.getInstance().importGuest();
			response = "data was imported from the csv file";
			break;
			
		case "RemoveGuest":
			id = Integer.valueOf(arrey[1]);
			Hotel.getInstance().remuveGuest(id);
			response = "guest was removed";
			break;
			//Room Blok
		case "AddRoom":
			copacity=Integer.valueOf(arrey[1]);
			Integer numberOfStars=Integer.valueOf(arrey[2]);
			price=Double.valueOf(arrey[3]);
			Hotel.getInstance().addRoom(copacity, numberOfStars, price);
			response = "room was added";
			break;
			
		case "AllRooms":
			StringBuilder allRooms = new StringBuilder();
			 entity = Hotel.getInstance().getAllRooms();
			for (int i = 0; i < entity.size(); i++) {
				allRooms.append(entity.get(i).toString());
				allRooms.append(";");
				}
			response = allRooms.toString();
			break;
			
		case "ChangeRoomPrice":
			 id = Integer.valueOf(arrey[1]);
			 price=Double.valueOf(arrey[2]);
		   	Hotel.getInstance().chengePriceOfRoom(id, price);
		   	response = "New prise of room "+id+" was "+price;
		   	break;
		   	
		case "ChangeRoomStatus":
			 id=Integer.valueOf(arrey[1]);
			 Integer n=Integer.valueOf(arrey[2]);
			 Hotel.getInstance().changeRoomStatus(id, n);
			 response = "room status was changed";
			 break;
			 
		case "Clone":
			id=Integer.valueOf(arrey[1]);
			String answer = arrey[2];
			copacity=Integer.valueOf(arrey[3]);
			Integer stars=Integer.valueOf(arrey[4]);
			price=Double.valueOf(arrey[5]);
			
			try {
				Hotel.getInstance().clone(id, answer, copacity, stars, price);
				response = "Room was cloned";
			} catch (CloneNotSupportedException e) {
				Printer.println("CloneNotSupportedException "+e.toString());
				logger.error("Exception: "+e.getMessage());
			}
			break;
			
		case "ExportRoom":
			Hotel.getInstance().exportRooms();
			response = "Export was successful";
			break;
			
		case "FreeRooms":
			StringBuilder freeRooms = new StringBuilder();
			 entity = Hotel.getInstance().getFreeRooms();
			for (int i = 0; i < entity.size(); i++) {
				freeRooms.append(entity.get(i).toString());
				freeRooms.append(";");
				}
			response = freeRooms.toString();
			break;
			
		case "ImportRoom":
			Hotel.getInstance().importRooms();
			response = "Import was successful";
			break;
			
		case "LastGuests":
			id=Integer.valueOf(arrey[1]);
			StringBuilder lastGuests = new StringBuilder();
			 entity = Hotel.getInstance().getLastGuests(id);
			for (int i = 0; i < entity.size(); i++) {
				lastGuests.append(entity.get(i).toString());
				lastGuests.append(";");
				}
			response = "last guests of the room "+id+";"+lastGuests.toString();
			break;
			
		case "NumberOfFreeRooms":
			response = "Number of free rooms "+Hotel.getInstance().getNumberOfFreeRooms();
			break;
			
		case "NumberOfRooms":
			response = "Number of rooms "+Hotel.getInstance().getNumberOfRooms();
			break;
			
		case "RoomById":
			id=Integer.valueOf(arrey[1]);
			response = Hotel.getInstance().getRoonById(id).toString();
			break;
			
		case "SortRoomsByCopacity":
			StringBuilder byCopacity = new StringBuilder();
			 entity = Hotel.getInstance().sortedRoomsByCopaciti();
			for (int i = 0; i < entity.size(); i++) {
				byCopacity.append(entity.get(i).toString());
				byCopacity.append(";");
			}
			response = byCopacity.toString();
			break;
			
		case "SortRoomsByPrice":
			StringBuilder byPrice = new StringBuilder();
			 entity = Hotel.getInstance().sortedRoomsByPrice();
			for (int i = 0; i < entity.size(); i++) {
				byPrice.append(entity.get(i).toString());
				byPrice.append(";");
			}
			response = byPrice.toString();
			break;
			
		case "SortRoomsByStars":
			StringBuilder byStars = new StringBuilder();
			 entity = Hotel.getInstance().sortedRoomsByStars();
			for (int i = 0; i < entity.size(); i++) {
				byStars.append(entity.get(i).toString());
				byStars.append(";");
			}
			response = byStars.toString();
			break;
			//History Blok
		case "EvictGuest":
			id= Integer.valueOf(arrey[1]);
			roomId=Integer.valueOf(arrey[2]);
			if(Hotel.getInstance().getGuestById(id)!=null) {
				Hotel.getInstance().evictGuestFromRoom(id, roomId);
				response = "guest is evicted";
				}else {
					response = "Incorrect guest id, or room id";
				}
			break;
			
		case "GetGuestsOfAllRooms":
			StringBuilder guestsOfRooms = new StringBuilder();
			 entity = Hotel.getInstance().getGuestsRooms();
			for (int i = 0; i < entity.size(); i++) {
				guestsOfRooms.append(entity.get(i).toString());
				guestsOfRooms.append(";");
			}
			response = guestsOfRooms.toString();
			break;
			
		case "FreeRoomOnDate":
			Integer day=Integer.valueOf(arrey[1]);
			Integer manth=Integer.valueOf(arrey[2]);
			Integer year=Integer.valueOf(arrey[3]);
			StringBuilder FreeRoomOnDate = new StringBuilder();
			 entity = Hotel.getInstance().getFreeRoomsOnDate( new GregorianCalendar(year,manth,day));
			for (int i = 0; i < entity.size(); i++) {
				FreeRoomOnDate.append(entity.get(i).toString());
				FreeRoomOnDate.append(";");
			}
			response = FreeRoomOnDate.toString();
			break;
			
		case "SettleGuestInRoom":
			id= Integer.valueOf(arrey[1]);
			roomId=Integer.valueOf(arrey[2]);
			Integer arivalDay=Integer.valueOf(arrey[3]);
			Integer arivalMonth=Integer.valueOf(arrey[4]);
			Integer arivalYear=Integer.valueOf(arrey[5]);
			Integer evictDay=Integer.valueOf(arrey[6]);
			Integer evictMonth=Integer.valueOf(arrey[7]);
			Integer evictYear=Integer.valueOf(arrey[8]);
			if(Hotel.getInstance().getGuestById(id)!=null &&Hotel.getInstance().getRoonById(roomId)!=null) {
				Hotel.getInstance().settleGuestInRoom(id, roomId, new GregorianCalendar(arivalYear, arivalMonth, arivalDay),
						                                                  new GregorianCalendar(evictYear, evictMonth, evictDay));
				response = "Guest "+id+" was settled in room "+roomId;
				}else {
					response = "Incorrect input data";
				}
			break;
			
		//Option Blok
		case "AddOption":
			price= Double.valueOf(arrey[2]);
			Hotel.getInstance().addOption(arrey[1], price);
			response = "Option was added";
			break;
			
		case "ExportOptions":
			Hotel.getInstance().exportOptions();
			response = "Export was successful";
			break;
			
		case "ImportOptions":
			Hotel.getInstance().importOptions();
			response = "Import was successful";
			break;
			
		case "ShowAllOptions":
			
			StringBuilder allOptions = new StringBuilder();
			 entity = Hotel.getInstance().getAllOptions();
			for (int i = 0; i < entity.size(); i++) {
				allOptions.append(entity.get(i).toString());
				allOptions.append(";");
			}
			response = allOptions.toString();
			break;
			
		case "ShowOptionById":
			id = Integer.valueOf(arrey[1]);
		    response = Hotel.getInstance().getOptionById(id).toString();
			break;
		}
		
		return response;
		
	}

}
