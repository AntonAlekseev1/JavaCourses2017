package com.hotel.di;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hotel.api.dao.IConnectorDao;
import com.hotel.api.dao.IGuestDAO;
import com.hotel.api.dao.IHistoryDAO;
import com.hotel.api.dao.IOptionDAO;
import com.hotel.api.dao.IRoomDAO;
import com.hotel.api.service.IGuestService;
import com.hotel.api.service.IHistoryService;
import com.hotel.api.service.IOptionService;
import com.hotel.api.service.IRoomService;

public class DependecyInjector {
	private final static Logger logger = Logger.getLogger(DependecyInjector.class);
	private static Map<Class<?>, String> dependencyMap = new HashMap<>();

	public static Map<Class<?>, String> fillMap() {
		if (dependencyMap.size() == 0) {
			String roomDatabaseDao = "com.hotel.dao.RoomDAO";
			String guestDatabaseDao = "com.hotel.dao.GuestDAO";
			String optionDatabaseDao = "com.hotel.dao.OptionDAO";
			String historyDatabaseDao = "com.hotel.dao.HistoryDAO";

			String roomService = "com.hotel.service.RoomService";
			String guestService = "com.hotel.service.GuestService";
			String optionService = "com.hotel.service.OptionService";
			String historyService = "com.hotel.service.HistoryService";

			String connectorDao = "com.hotel.dao.connection.Connector";

			dependencyMap.put(IRoomDAO.class, roomDatabaseDao);
			dependencyMap.put(IGuestDAO.class, guestDatabaseDao);
			dependencyMap.put(IOptionDAO.class, optionDatabaseDao);
			dependencyMap.put(IHistoryDAO.class, historyDatabaseDao);

			dependencyMap.put(IRoomService.class, roomService);
			dependencyMap.put(IGuestService.class, guestService);
			dependencyMap.put(IOptionService.class, optionService);
			dependencyMap.put(IHistoryService.class, historyService);

			dependencyMap.put(IConnectorDao.class, connectorDao);
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
			logger.error(e);
		}
		return obj;
	}

}
