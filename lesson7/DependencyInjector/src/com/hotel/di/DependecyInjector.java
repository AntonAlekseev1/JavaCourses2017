package com.hotel.di;

import java.util.HashMap;
import java.util.Map;

import com.hotel.api.repository.IGuestRepository;
import com.hotel.api.repository.IHistoryRepository;
import com.hotel.api.repository.IOptionRepository;
import com.hotel.api.repository.IRoomRepository;
import com.hotel.repository.GuestRepository;
import com.hotel.repository.HistoryRepository;
import com.hotel.repository.OptionRepository;
import com.hotel.repository.RoomRepository;

public class DependecyInjector {
	private static Map<Class<?>, Object> dependencyMap = new HashMap<>();
	
	public static void fillMap() {
		Object roomRepository = RoomRepository.getInstance();
		Object guestRepository = GuestRepository.getInstance();
		Object optionRepository = OptionRepository.getInstance();
		Object historyRepository = HistoryRepository.getInstance();
		dependencyMap.put(IRoomRepository.class,roomRepository);
		dependencyMap.put(IGuestRepository.class, guestRepository);
		dependencyMap.put(IOptionRepository.class, optionRepository);
		dependencyMap.put(IHistoryRepository.class, historyRepository);
	}
	
	public static Object getRepository(Class<?> name) {
		fillMap();
		return dependencyMap.get(name);
	}
}
