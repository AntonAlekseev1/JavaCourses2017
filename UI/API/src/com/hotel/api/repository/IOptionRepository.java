package com.hotel.api.repository;

import java.util.List;

import com.hotel.api.been.IOption;

public interface IOptionRepository extends IRepository {
	
	public List<IOption> getOption();
	
	public void setOptions(List<IOption>options);
	
	public void addOption(IOption option);
	
	public IOption getOptionById(Integer id);
	
	public void removeOption(IOption option);

}
