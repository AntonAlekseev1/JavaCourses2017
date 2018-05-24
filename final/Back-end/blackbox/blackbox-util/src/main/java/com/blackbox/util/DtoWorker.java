package com.blackbox.util;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;

import com.blackbox.api.dto.IDto;
import com.blackbox.beans.generic.Entity;

public class DtoWorker {
	
	private static DozerBeanMapper dozer = new DozerBeanMapper();
	
	public static List<IDto> listToDto(List<? extends Entity> list, Class<? extends IDto> clazz) {
		
		List<IDto> dtoList = new ArrayList<>(); 
		for(Entity entyty: list) {
			dtoList.add(dozer.map(entyty, clazz));
		}
		return dtoList;
		
	}

}
