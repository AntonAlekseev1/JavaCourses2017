package com.hotel.api.service;

import java.util.List;

import com.hotel.been.Option;

public interface IOptionService {

	public List<Option> getOption() throws Exception;

	public void addOption(Option option) throws Exception;

	String importOptions(String path) throws Exception;

	String exportOptions(String path) throws Exception;

}
