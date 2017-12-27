package com.hotel.api.repository;

import java.util.List;

import com.hotel.api.been.IOption;

public interface IOptionRepository {
	
	public List<IOption> getOption();
	
	public void addOption(IOption option);
	
	public IOption getOptionById(Integer id);
	
	public void removeOption(IOption option);
	
	public void readFromFile(String path);

}
