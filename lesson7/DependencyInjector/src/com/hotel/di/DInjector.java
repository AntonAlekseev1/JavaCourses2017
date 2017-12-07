package com.hotel.di;

import com.hotel.api.repository.ARepository;
import com.hotel.api.repository.IGuestRepository;
import com.hotel.api.repository.IHistoryRepository;
import com.hotel.api.repository.IOptionRepository;
import com.hotel.api.repository.IRepository;
import com.hotel.api.repository.IRoomRepository;
import com.hotel.repository.GuestRepository;
import com.hotel.repository.HistoryRepository;
import com.hotel.repository.OptionRepository;
import com.hotel.repository.RoomRepository;

public class DInjector {
	
	public static ARepository inject(Class<?extends IRepository> name) {
		
		if(name.equals(IRoomRepository.class)) {
			return  RoomRepository.getInstance();
		}else if(name.equals(IGuestRepository.class)) {
			return GuestRepository.getInstance();
		}else if(name.equals(IOptionRepository.class)) {
			return OptionRepository.getInstance();
		}else if(name.equals(IHistoryRepository.class)){
			return HistoryRepository.getInstance();
		}
		return null;
		
	}

}
