package com.hotel.di;

import java.util.HashMap;
import java.util.Map;

import com.hotel.api.repository.IGuestRepository;
import com.hotel.api.repository.IHistoryRepository;
import com.hotel.api.repository.IOptionRepository;
import com.hotel.api.repository.IRoomRepository;

public class DInjector {
	
	public  Map<Class<?>, String> dependencyMap = new HashMap<>();
	
	public  void fillMap() {
		String roomRepository = "com.hotel.repository.RoomRepository";
		String guestRepository = "com.hotel.repository.GuestRepository";
		String optionRepository = "com.hotel.repository.OptionRepository";
		String historyRepository = "com.hotel.repository.HistoryRepository";
		dependencyMap.put(IRoomRepository.class,roomRepository);
		dependencyMap.put(IGuestRepository.class, guestRepository);
		dependencyMap.put(IOptionRepository.class, optionRepository);
		dependencyMap.put(IHistoryRepository.class, historyRepository);
	//Class<?> c = c.forName(className)	
	}
	
	public static  Object inject(Class<?>name) {
		Object obj=null;
	//	String className = dependencyMap.get(name);
		try {
			Class<?> c = Class.forName("com.hotel.repository.RoomRepository");
				 obj = c.newInstance();
			
		} catch (ClassNotFoundException|InstantiationException|IllegalAccessException e) {
		
		}
		return obj;
		
	}

}
