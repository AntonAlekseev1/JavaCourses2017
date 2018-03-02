package com.hotel.di;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hotel.api.repository.IGuestRepository;
import com.hotel.api.repository.IHistoryRepository;
import com.hotel.api.repository.IOptionRepository;
import com.hotel.api.repository.IRoomRepository;
import com.hotel.api.service.IGuestService;
import com.hotel.api.service.IHistoryService;
import com.hotel.api.service.IOptionService;
import com.hotel.api.service.IRoomService;

public class DependecyInjector {
	private final static Logger logger = Logger.getLogger(DependecyInjector.class);
	private static Map<Class<?>, String> dependencyMap = new HashMap<>();
	
	public static Map<Class<?>,String> fillMap() {
		if(dependencyMap.size()==0) {
		String roomRepository = "com.hotel.repository.RoomRepository";
		String guestRepository = "com.hotel.repository.GuestRepository";
		String optionRepository = "com.hotel.repository.OptionRepository";
		String historyRepository = "com.hotel.repository.HistoryRepository";
		
		String roomService = "com.hotel.service.RoomService";
		String guestService = "com.hotel.service.GuestService";
		String optionService = "com.hotel.service.OptionService";
		String historyService = "com.hotel.service.HistoryService";
		
		dependencyMap.put(IRoomRepository.class,roomRepository);
		dependencyMap.put(IGuestRepository.class, guestRepository);
		dependencyMap.put(IOptionRepository.class, optionRepository);
		dependencyMap.put(IHistoryRepository.class, historyRepository);
		
		dependencyMap.put(IRoomService.class,roomService);
		dependencyMap.put(IGuestService.class,guestService);
		dependencyMap.put(IOptionService.class,optionService);
		dependencyMap.put(IHistoryService.class,historyService);
		}
		return dependencyMap;
	}
	
	public static Object inject(Class<?> name) {
		fillMap();
		Object obj = null;
		String className = dependencyMap.get(name);
		try {
			Class<?> implClass = Class.forName(className);
			Method method = implClass.getMethod("getInstance");
			obj = method.invoke(implClass);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return obj;
	}
	
}
