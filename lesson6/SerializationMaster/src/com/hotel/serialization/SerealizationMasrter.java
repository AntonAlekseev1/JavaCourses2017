package com.hotel.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.hotel.api.been.IEntity;
import com.hotel.api.been.IGuest;
import com.hotel.api.been.IHistory;
import com.hotel.api.been.IOption;
import com.hotel.api.been.IRoom;
import com.hotel.configurations.Configuration;
import com.hotel.repository.GuestRepository;
import com.hotel.repository.HistoryRepository;
import com.hotel.repository.OptionRepository;
import com.hotel.repository.RoomRepository;

public class SerealizationMasrter {
	
	private static final String PATH_TO_FILE= String.valueOf(Configuration.getProperties("PATH_TO_FILE"));
	
	public  SerealizationMasrter() {
		Configuration.loadConfiguration();
	}
	
	public static void marshaling(List<IGuest>guests, List<IRoom>rooms, List<IOption>options, List<IHistory>histories)
			throws IOException, FileNotFoundException {
		
		List<List<? extends IEntity>>entities=new ArrayList<>();
		try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream( PATH_TO_FILE)))  {
			entities.add(guests);
			entities.add(rooms);
			entities.add(options);
			entities.add(histories);
			outStream.writeObject(entities);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void demarshaling() throws IOException, ClassNotFoundException {
		
		try(ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(PATH_TO_FILE))) {
			List<List<? extends IEntity>> entities = (List<List<? extends IEntity>>) inStream.readObject();
			GuestRepository.getInstance().setGuests((List<IGuest>)entities.get(0));
			RoomRepository.getInstance().setRooms((List<IRoom>) entities.get(1));
			OptionRepository.getInstance().setOptions((List<IOption>) entities.get(2));
			HistoryRepository.getInstance().setHistory((List<IHistory>) entities.get(3));		
		}
	}

}
